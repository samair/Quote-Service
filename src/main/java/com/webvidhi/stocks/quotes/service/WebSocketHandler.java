package com.webvidhi.stocks.quotes.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.webvidhi.stocks.quotes.model.GlobalQuote;
import com.webvidhi.stocks.quotes.model.SessionSubscription;
import com.webvidhi.stocks.quotes.model.TradierPojo;

@Service
public class WebSocketHandler {
	
	@Autowired
    private SimpMessagingTemplate broker;
	
	@Autowired
	private SessionSubscription subscribers;
	
	@Autowired
	private QuoteQueryDispatcher dispatcher;

	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Scheduled(fixedDelay = 5000)
	public void addUpdateQuotes() {
		
		System.out.println("Timer hit!!");
		sendEvnets();
	}

	 //@SendTo("/topic/events")
	  public void sendEvnets() {
		  
		  for (Entry<String, List<String>> subscriber : subscribers.getSubscribers().entrySet())
		  {
			  String topicName = "/topic/" + subscriber.getKey();
			  System.out.println("Sending to : "+topicName);
			  TradierPojo p= dispatcher.getQouteInfoMulti(subscriber.getValue());
			  broker.convertAndSend(topicName, p); 
		  }
		  
	  }
}
