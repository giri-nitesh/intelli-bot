package com.discord.intelli_bot;

import com.discord.intelli_bot.events.HelloEvent;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class App {
	public static void main(String[] args) throws Exception {

		JDA jda = new JDABuilder("token").build();
		jda.addEventListener(new HelloEvent());
	}
}
