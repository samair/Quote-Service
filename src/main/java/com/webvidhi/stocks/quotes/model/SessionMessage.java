package com.webvidhi.stocks.quotes.model;

import java.util.List;

public class SessionMessage {
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<String> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<String> symbols) {
		this.symbols = symbols;
	}

	private String sessionId;
	
	private List<String> symbols;

}
