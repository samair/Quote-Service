package com.webvidhi.stocks.quotes.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class SessionSubscription {
	
	public SessionSubscription() {
		super();
		subscribers = new ConcurrentHashMap<>();
	}

	ConcurrentHashMap<String,List<String>> subscribers;

	public Map<String, List<String>> getSubscribers() {
		return subscribers;
	}

	public void addSubscription(String sessionId, List<String> symbols) {
		
		subscribers.put(sessionId, symbols);
	}
	
	public void removeSubscription(String sessionId) {
		subscribers.remove(sessionId);
	}
	
	

}
