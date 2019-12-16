package com.discord.intelli_bot.utils;

import java.util.Set;

/**
 * Util class to suggest search on the previos search history. This works by
 * substring matching
 * 
 * @author Nitesh
 *
 */
public class IntelliRecommendationUtil {
	private Set<String> searchedItems;

	public IntelliRecommendationUtil(Set<String> searchedItems) {
		this.searchedItems = searchedItems;
	}

	public String recommend(String keyTerm) {
		StringBuilder builder = new StringBuilder();
		for (String item : searchedItems) {
			if (item.contains(keyTerm)) {
				builder.append(item);
				builder.append("\n");
			}
		}
		return builder.toString();

	}

}
