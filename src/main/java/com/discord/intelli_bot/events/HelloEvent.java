package com.discord.intelli_bot.events;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class HelloEvent extends ListenerAdapter {

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String messageSent = event.getMessage().getContentRaw();
		if (messageSent.equalsIgnoreCase("hello")) {
			event.getChannel().sendMessage("hello").queue();
		}
	}
}
