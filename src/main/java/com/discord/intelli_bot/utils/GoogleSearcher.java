package com.discord.intelli_bot.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.discord.intelli_bot.entities.SearchResponse;
import com.discord.intelli_bot.entities.SearchResult;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Search;
import com.google.gson.Gson;

/***
 * Searches on Google through Google Custom Search Engine. Google's CSE is not
 * same as the Google Site search we do.
 * 
 * @author Nitesh
 *
 */
public class GoogleSearcher {

	private String apiKey;
	private String customSearchEngineKey;

	public GoogleSearcher() throws URISyntaxException, IOException {
		PropertiesHandler propertiesHandler = new PropertiesHandler(AppConstants.PROPERTIES_FILE);
		this.apiKey = propertiesHandler.getProperties().getProperty("google_api_key");
		this.customSearchEngineKey = propertiesHandler.getProperties().getProperty("google_cse_key");
		if (apiKey == null || customSearchEngineKey == null) {
			// throw keys not found exception
		}
	}

	/**
	 * 
	 * @param keywords represents the terms to be included in google search
	 * @return Top 5 links that we would get on searching google
	 * @throws IOException
	 */

	public String search(String keywords) throws IOException {
		String searchString = buildSearchString(keywords);
		SearchResponse searchResponse;
		List<SearchResult> results = null;
		try {
			searchResponse = googleCustomSearch(searchString);
			results = searchResponse.getItems();
		} catch (Exception e) {
			e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();
		int resultCount = 0;
		for (SearchResult result : results) {
			if (resultCount < AppConstants.RESULT_SET_SIZE) {
				builder.append(result.getLink() + "\n");
				System.out.println(result.getSnippet());
				System.out.println(result.getTitle());
				// all attributes:
				System.out.println(result.toString());
				resultCount++;
			} else
				break;
		}
		// return builder.toString();
		return builder.toString();

	}

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	private SearchResponse googleCustomSearch(String keyword) {
		Customsearch customsearch = null;
		try {
			customsearch = new Customsearch(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
				public void initialize(HttpRequest httpRequest) {
					try {
						// set connect and read timeouts
						httpRequest.setConnectTimeout(AppConstants.HTTP_REQUEST_TIMEOUT);
						httpRequest.setReadTimeout(AppConstants.HTTP_REQUEST_TIMEOUT);

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		SearchResponse searchResponse = null;
		try {
			Customsearch.Cse.List list = customsearch.cse().list(keyword);
			list.setKey(apiKey);
			list.setCx(customSearchEngineKey);
			Search results = list.execute();
			searchResponse = new Gson().fromJson(results.toString(), SearchResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchResponse;
	}

	/**
	 * Prepares the string @param searchString to the keywords that would be used
	 * for Google search
	 * 
	 * @param searchString
	 * @return String with search keywords
	 */
	private String buildSearchString(String searchString) {
		String newSearchString = searchString.replace(" ", "%20");
		System.out.println("Seacrh URL: " + newSearchString);
		return newSearchString;
	}

}
