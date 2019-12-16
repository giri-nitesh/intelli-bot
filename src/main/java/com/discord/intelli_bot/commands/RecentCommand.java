package com.discord.intelli_bot.commands;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.discord.intelli_bot.dal.SearchedQueriesDaoImpl;
import com.discord.intelli_bot.utils.IntelliRecommendationUtil;
import com.discord.intelli_bot.utils.SearchTracker;

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
		SearchedQueriesDaoImpl daoImpl = new SearchedQueriesDaoImpl();
		SearchTracker searchTracker = new SearchTracker(daoImpl);
		if (parameters.length < 1)
			return searchTracker.getSearchedItems();
		else {
			String keyTerm = parameters[0];
			Set<String> searchedItems = searchTracker.getSearchedItemsSet();
			IntelliRecommendationUtil recommendationUtil = new IntelliRecommendationUtil(searchedItems);
			return recommendationUtil.recommend(keyTerm);
		}
	}

	@Override
	public String getHelpMessage() {
		String s = "```Markdown";
		s += "\nCommand Info: !" + getName() + "\n" + StringUtils.repeat("=", 15 + getName().length());
		s += "\n* " + getShortDescription();
		s += "\n* <Parameters: " + getRequiredParameterCount() + ">" + ": Use this parameter"
				+ " to get suggestions related to this. If not specified, it will retrieve all searched items.";
		s += "\n*";
		s += "\n*\n[Example]: " + getExampleUsage();
		s += "```";
		return s;
	}

	@Override
	public int getRequiredParameterCount() {
		return 1;
	}

}
