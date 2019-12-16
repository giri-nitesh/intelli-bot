package com.discord.intelli_bot.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpCommand extends Command {

	@Override
	public String execute(MessageReceivedEvent event, String... parameters) throws Exception {
		return "Following are the ways to execute the command";
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
		return 1;
	}

}
