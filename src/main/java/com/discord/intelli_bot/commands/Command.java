package com.discord.intelli_bot.commands;

import org.apache.commons.lang3.StringUtils;

import com.discord.intelli_bot.exceptions.CommandExecutionException;
import com.discord.intelli_bot.listeners.CommandListener;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Abstract base class for commands. Extend this class and add an instance of
 * that class to the {@link CommandListener} to create a new command for the
 * Intelli-Bot.
 *
 * @author Nitesh
 *
 */
public abstract class Command {

	/**
	 * Returns the command's name without prefix. Example: if you write "google" and
	 * the currently configured prefix is "!", the bot will execute your command
	 * when a user types "!google".
	 *
	 * @return The command's name
	 */
	public abstract String getName();

	/**
	 * Returns a short (1 sentence) description of the command. Used by the
	 * {@link HelpCommand} for displaying a list of available commands.
	 *
	 * @return Short description
	 */
	public abstract String getShortDescription();

	/**
	 * Executes the command and returns a String to display in Discord as response.
	 *
	 * @param event      The {@link MessageReceivedEvent} that contains the command
	 * @param parameters String Array containing the command's parameters.
	 * @return A message that will be send to the text chat in which the command has
	 *         been called
	 * @throws Exception Might throw any kind of exception while executing the
	 *                   command
	 */
	public abstract String execute(MessageReceivedEvent event, String... parameters) throws Exception;

	/**
	 * Similar to {@link execute(MessageReceivedEvent, String...)}, but is called
	 * when the command is used in a private chat between a user and the bot.
	 * Currently not supported.
	 *
	 * @param event      The {@link MessageReceivedEvent} that contains the command
	 * @param parameters String Array containing the command's parameters.
	 * @return A message that will be send to the private chat in which the command
	 *         has been called
	 * @throws Exception Might throw any kind of exception while executing the
	 *                   command
	 */
	public String executePrivate(MessageReceivedEvent event, String... parameters) throws Exception {
		throw new CommandExecutionException("This functionality is not yet implemented :(");
	}

	/**
	 * Returns how many parameters the command requires.
	 *
	 * @return Number of required parameters
	 */
	public int getRequiredParameterCount() {
		return 0;
	}

	/**
	 * If the command's parameters should not be delimited by spaces, you can
	 * override this method.
	 *
	 * @return The char that parameters are delimited by, for example '='
	 */
	public char getParameterDelimiter() {
		return ' ';
	}

	/**
	 * Returns an example of how the command can be used. Will be displayed when the
	 * command is called using the "-help" option.
	 *
	 * @return The example
	 */
	public String getExampleUsage() {
		StringBuilder bob = new StringBuilder("!" + getName());
		for (int i = 0; i < getRequiredParameterCount(); i++) {
			if (i == 0) {
				bob.append(" [parameter0]");
			} else {
				bob.append(getParameterDelimiter() + "[parameter" + i + "]");
			}
		}
		return bob.toString();
	}

	/**
	 * Returns the message that will be displayed when the command is called using
	 * the "-help" option.
	 * 
	 * @return The help message
	 */
	public String getHelpMessage() {
		String s = "```Markdown";
		s += "\nCommand Info: !" + getName() + "\n" + StringUtils.repeat("=", 15 + getName().length());
		s += "\n* " + getShortDescription();
		s += "\n* <Parameters: " + getRequiredParameterCount() + ">";
		s += "\n*";
		s += "\n*\n[Example]: " + getExampleUsage();
		s += "```";
		return s;
	}

	@Override
	public String toString() {
		return "BasicCommand [getName()=" + getName() + ", getHelpMessage()=" + getHelpMessage()
				+ ", getRequiredParameterCount()=" + getRequiredParameterCount() + ", getParameterDelimiter()="
				+ getParameterDelimiter() + ", getErrorMessage()=" + "]";
	}
}
