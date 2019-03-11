package com.webvidhi.stocks.quotes.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TradierQuotes {

	@JsonProperty("quote")
	GlobalQuote quoteSingle;
	
	private List<GlobalQuote> quote = null;

	public GlobalQuote getQuote() {
	return quoteSingle;
	}


	public void setQuote(List<GlobalQuote> quote) {
	this.quote = quote;
	}
	
	@JsonProperty("quote")
	public void setQuote(GlobalQuote quote) {
	this.quoteSingle = quote;
	}
}
