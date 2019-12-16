package com.discord.intelli_bot;

import com.discord.intelli_bot.listeners.CommandListener;
import com.discord.intelli_bot.utils.AppConstants;
import com.discord.intelli_bot.utils.PropertiesHandler;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

/**
 * 
 * @author Nitesh
 */
public class IntelliBot {
	public static void main(String[] args) throws Exception {

		PropertiesHandler propertiesHandler = new PropertiesHandler(AppConstants.PROPERTIES_FILE);
		String tokenString = propertiesHandler.getProperties().getProperty("bot_token_string");
		JDA jda = new JDABuilder(tokenString).build();
		CommandListener commandListener = new CommandListener(jda);
		jda.addEventListener(commandListener);
	}
}
