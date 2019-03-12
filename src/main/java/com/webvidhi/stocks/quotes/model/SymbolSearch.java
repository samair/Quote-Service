package com.webvidhi.stocks.quotes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SymbolSearch {

	@JsonProperty("securities")
	private SearchResult results;

	public SearchResult getResults() {
		return results;
	}
	
	@JsonProperty("securities")
	public void setResults(SearchResult results) {
		this.results = results;
	}
	
	
}
