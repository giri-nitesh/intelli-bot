package com.discord.intelli_bot.entities;

import java.util.List;

public class SearchResponse {
	private Context context;
	private List<SearchResult> items;

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public List<SearchResult> getItems() {
		return items;
	}

	public void setItems(List<SearchResult> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "SearchResponse [title=" + context.getTitle() + ", items=" + items + "]";
	}

	class Context {
		private String title;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		@Override
		public String toString() {
			return "Context [title=" + title + "]";
		}

	}

}
