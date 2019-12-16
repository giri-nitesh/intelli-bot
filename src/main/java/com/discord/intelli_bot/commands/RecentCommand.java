package com.discord.intelli_bot.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RecentCommand extends Command {

	@Override
	public String getName() {
		return "recent";
	}

	@Override
	public String getShortDescription() {
		return "Returns the recent search items";
	}

	@Override
	public String execute(MessageReceivedEvent event, String... parameters) throws Exception {

		return null;
	}

}
