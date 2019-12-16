package com.discord.intelli_bot.listeners;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Basic listener that contains method for reacting to private and group chat
 * messages. This is the base class for any of the Listener that we may require.
 *
 * @author Nitesh
 *
 */
public abstract class ChatListener extends ListenerAdapter {

	protected JDA jda;

	/**
	 * @param jda
	 */
	public ChatListener(JDA jda) {
		this.jda = jda;
	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 */
	protected void handlePublicMessage(MessageReceivedEvent event) throws Exception {
		// empty per default
	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 */
	protected void handlePrivateMessage(MessageReceivedEvent event) throws Exception {
		// empty per default
	}

	/**
	 * 
	 * @param event
	 * @param message
	 */
	protected void send(MessageReceivedEvent event, String message) {
		event.getChannel().sendMessage(message).queue();
	}

	/**
	 * 
	 * @param event
	 * @param message
	 */
	protected void sendPrivate(MessageReceivedEvent event, String message) {
		event.getPrivateChannel().sendMessage(message).queue();
	}

}
