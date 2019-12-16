package com.discord.intelli_bot.listeners;

import java.util.ArrayList;
import java.util.List;

import com.discord.intelli_bot.commands.Command;
import com.discord.intelli_bot.commands.HelpCommand;
import com.discord.intelli_bot.commands.RecentCommand;
import com.discord.intelli_bot.commands.SearchCommand;
import com.discord.intelli_bot.exceptions.CommandExecutionException;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Listens to and executes commands.
 *
 * @author Nitesh
 *
 */
public class CommandListener extends ChatListener {

	private List<Command> commands = new ArrayList<Command>();

	/**
	 * @param jda
	 */
	public CommandListener(JDA jda) {
		super(jda);
		initialiseCommands();
	}

	private void initialiseCommands() {
		HelpCommand helpCommand = new HelpCommand(this);
		SearchCommand searchCommand = new SearchCommand();
		RecentCommand recentCommand = new RecentCommand();
		commands.add(helpCommand);
		commands.add(searchCommand);
		commands.add(recentCommand);
	}

	/**
	 * @return the commands
	 */
	public List<Command> getCommands() {
		return commands;
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().getId().equals(jda.getSelfUser().getId())) {
			return;
		}
		try {
			if (event.isFromType(ChannelType.PRIVATE)) {
				handlePrivateMessage(event);
			} else {
				handlePublicMessage(event);
			}
		} catch (CommandExecutionException e) {
			System.out.println("CommandExecutionException: " + e.getMessage());
			send(event, ":no_entry: Error: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			send(event, ":no_entry: Error: " + e.getMessage());
		}
	}

	@Override
	protected void handlePrivateMessage(MessageReceivedEvent event) throws Exception {
	}

	@Override
	protected void handlePublicMessage(MessageReceivedEvent event) throws Exception {
		String commandString = event.getMessage().getContentRaw();
		// if the message recieved is a single plain text
		if (commandString.equals("hi")) {
			send(event, "hey");
			send(event, "\n To know more about me try typing !help");
			return;
			// if the message recieved contains ! => the message is some command
		} else if (commandString.toString().contains("!")) {
			String[] parmas = commandString.split(" ");
			String commandName = parmas[0].replace('!', ' ').trim();
			List<Command> commands = getCommands();
			for (Command command : commands) {
				if (command.getName().equals(commandName)) {
					send(event, handleCommand(event, command));
					return;
				}
			}
			throw new CommandExecutionException("Invalid command used. Please refer !help for usage.");
		}
	}

	private String handleCommandWithParams(MessageReceivedEvent event, Command command) throws Exception {
		String commandString = event.getMessage().getContentRaw();
		String[] parameters = commandString.replaceFirst("!" + command.getName() + " ", "")
				.split(command.getParameterDelimiter() + "");
		if (parameters.length < command.getRequiredParameterCount()) {
			throw new CommandExecutionException(
					"Not enough parameters were given! Try `!" + command.getName() + " -help`");
		}
		return executeCommand(event, command, parameters);
	}

	private String handleCommand(MessageReceivedEvent event, Command command) throws Exception {
		String commandString = event.getMessage().getContentRaw();
		String[] commandParams = commandString.split(" ");
		int noOfParameters = commandParams.length;
		if (noOfParameters > 1) {
			return handleCommandWithParams(event, command);
		}
		return executeCommand(event, command);
	}

	private String executeCommand(MessageReceivedEvent event, Command command, String... parameters) throws Exception {
		return command.execute(event, parameters);
	}

}
