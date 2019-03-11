package com.webvidhi.stocks.quotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


public class TradierPojo {
	
	@JsonProperty("quotes")
	private TradierQuotes quotes;


	@JsonProperty("quotes")
	public TradierQuotes getQuotes() {
	return quotes;
	}

	@JsonProperty("quotes")
	public void setQuotes(TradierQuotes quotes) {
	this.quotes = quotes;
	}


}
