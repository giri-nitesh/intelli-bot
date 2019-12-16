package com.discord.intelli_bot.commands;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.discord.intelli_bot.listeners.CommandListener;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpCommand extends Command {

	private CommandListener commandListener;

	public HelpCommand(CommandListener commandListener) {
		this.commandListener = commandListener;
	}

	@Override
	public String execute(MessageReceivedEvent event, String... parameters) throws Exception {
		String s = "";
		if (parameters.length < 1) {
			s = "```";
			s += "Following are the available commands that I can understand :)" + "\n" + "1. !google <param0> =>"
					+ "Searches on google and returns top 5 links" + "\n" + "\t\t\t" + "param0 " + "item to be searches"
					+ "\n" + "2. !recent => " + "Get the past searched items" + "\n" + "3. !help =>"
					+ "Get the usage of commands" + "\n" + "4. !help google =>" + "Get the usage of google command"
					+ "\n" + "5. !help recent =>" + "Get the usage of recent command";
			s += "```";
			return s;
		} else {
			String commandName = parameters[0];
			List<Command> list = commandListener.getCommands();
			for (Command command : list) {
				if (command.getName().equals(commandName)) {
					s = command.getHelpMessage();
				}
			}
		}
		return s;
	}

	@Override
	public String getName() {
		return "help";
	}

	@Override
	public String getShortDescription() {
		return "Sends you a list of available commands and their usages.";
	}

	@Override
	public int getRequiredParameterCount() {
		return 0;
	}

	@Override
	public String getHelpMessage() {
		String s = "```Markdown";
		s += "\nCommand Info: !" + getName() + "\n" + StringUtils.repeat("=", 15 + getName().length());
		s += "\n* " + getShortDescription();
		s += "\n* <Parameters: " + getRequiredParameterCount() + ">" + ": Get the usage of any command specified "
				+ "by this param";
		s += "\n*";
		s += "\n*\n[Example]: " + getExampleUsage();
		s += "```";
		return s;
	}

}
