package com.discord.intelli_bot.dal;

import java.util.List;

import com.discord.intelli_bot.entities.SearchResult;

/**
 * Represents the Data Access Layer to persist the search history. Implement it
 * for any persistence mechanism
 * 
 * @author Nitesh
 *
 */
public interface SearchedQueriesDao {
	/**
	 * Get all the items that has been searched on discord app.
	 * 
	 * @return Returns the history of searched items
	 */
	List<SearchResult> getAllSearchedQueries();

	/**
	 * Persist the searched items in the database
	 * 
	 * @param result
	 */
	void saveQuery(SearchResult result);
}
