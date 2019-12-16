package com.discord.intelli_bot.utils;

import java.io.IOException;

import com.discord.intelli_bot.dal.SearchedQueriesDaoImpl;
import com.discord.intelli_bot.entities.SearchResult;

/**
 * Utility class to retrieve and save the searches.
 * 
 * @author Nitesh
 *
 */
public class SearchTracker {
	private SearchedQueriesDaoImpl daoImpl;

	public SearchTracker(SearchedQueriesDaoImpl daoImpl) {
		this.daoImpl = daoImpl;
	}

	/**
	 * 
	 * @param searchString Saves the {@link SearchResult} in db
	 * @throws IOException
	 */
	public void persistInLocalDb(String searchString) throws IOException {
		SearchedQueriesDaoImpl daoImpl = new SearchedQueriesDaoImpl();
		daoImpl.saveRecordInDb(searchString);
	}

	/**
	 * Retrieves the searched items from history
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getSearchedItems() throws IOException {
		return daoImpl.readRecordsFromDb();
	}

}
