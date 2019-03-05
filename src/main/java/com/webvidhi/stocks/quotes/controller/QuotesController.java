package com.webvidhi.stocks.quotes.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webvidhi.stocks.quotes.model.GlobalQuote;
import com.webvidhi.stocks.quotes.query.QuoteQueryDispatcher;

@CrossOrigin
@RestController
@RequestMapping("/quotes/")
public class QuotesController {
	
	@GetMapping("{symbol}")
	public GlobalQuote getQoutes(@PathVariable String symbol) {
		
		return   QuoteQueryDispatcher.getQouteInformation(symbol);
	}

}
