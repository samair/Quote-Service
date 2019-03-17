package com.webvidhi.stocks.quotes.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller

public class QouteWSController {

    @MessageMapping("/quote-events")  
    @SendTo("/topic/events")
    public String quoteEvents(String str) {
    	
    	return "Changed!";
    	
    }
    
}
