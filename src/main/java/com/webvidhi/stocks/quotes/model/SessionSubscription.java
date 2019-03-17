package com.webvidhi.stocks.quotes.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class SessionSubscription {
	
	public SessionSubscription() {
		super();
		subscribers = new HashMap<>();
	}

	Map<String,List<String>> subscribers;

	public Map<String, List<String>> getSubscribers() {
		return subscribers;
	}

	public void addSubscription(String sessionId, List<String> symbols) {
		
		subscribers.put(sessionId, symbols);
	}
	
	

}
