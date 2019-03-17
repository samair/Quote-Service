package com.webvidhi.stocks.quotes.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class TradierQuotes {

	
	private List<GlobalQuote> quote = null;
	
	private boolean error;

	private boolean invalidSymbol;


	private Map<String, Object> additionalProperties = new HashMap<>();
	@JsonAnySetter
	public void setAdditionalProperties(String name, Object value) {
		this.additionalProperties.put(name, value);
		
	}
	@JsonAnyGetter
	public Map<String, Object>  getAdditionalProperties() {
		return additionalProperties;
	}
	
	public void setError(boolean isError) {
		this.error = isError;
	
	}
	public void setInvalidSymbol(boolean isInvalid) {
	  this.invalidSymbol = isInvalid;
		
	}
	
	public boolean isError() {
		return error;
	}
	
	public boolean isInvalidSymbol() {
		return invalidSymbol;
		
	}
	
	public boolean unmatchedSymbol() {
		
		return additionalProperties.containsKey("unmatched_symbols");
		
	}
}
