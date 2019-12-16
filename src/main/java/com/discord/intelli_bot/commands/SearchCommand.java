package com.discord.intelli_bot.commands;

import com.discord.intelli_bot.dal.SearchedQueriesDaoImpl;
import com.discord.intelli_bot.utils.GoogleSearcher;
import com.discord.intelli_bot.utils.SearchTracker;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SearchCommand extends Command {

	@Override
	public String getName() {
		return "search";
	}

	@Override
	public String getShortDescription() {
		return "Searches on the google through custom search with the keywords" + "specified after command";
	}

	@Override
	public String execute(MessageReceivedEvent event, String... parameters) throws Exception {
		SearchedQueriesDaoImpl daoImpl = new SearchedQueriesDaoImpl();
		SearchTracker searchTracker = new SearchTracker(daoImpl);
		if (parameters.length > 0) {
			String searchString = parameters[1];
			GoogleSearcher googleSearcher = new GoogleSearcher();
			searchTracker.persistInLocalDb(searchString);
			return googleSearcher.search(searchString);
		} else {
			return searchTracker.getSearchedItems();
		}
	}

	@Override
	public int getRequiredParameterCount() {
		return 1;
	}

}
