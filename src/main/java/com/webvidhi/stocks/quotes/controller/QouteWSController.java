package com.webvidhi.stocks.quotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.webvidhi.stocks.quotes.model.SessionMessage;
import com.webvidhi.stocks.quotes.model.SessionSubscription;

@Controller

public class QouteWSController {

	@Autowired
	SessionSubscription subscriber;
	
    @MessageMapping("/quote-events")  
    @SendTo("/topic/events")
    public String quoteEvents(String str) {
    	
    	return "Changed!";
    	
    }
    
    @MessageMapping("/start")
    public void  startSubscription(@Payload SessionMessage sessionMsg , SimpMessageHeaderAccessor headerAccessor) {
    	System.out.println("Recieved payload .." + sessionMsg.getSessionId());
    	headerAccessor.getSessionAttributes().put("sessionId", sessionMsg.getSessionId());
    	subscriber.addSubscription(sessionMsg.getSessionId(), sessionMsg.getSymbols());
    	
    }
    
}
