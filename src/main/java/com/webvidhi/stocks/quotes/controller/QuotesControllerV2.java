package com.webvidhi.stocks.quotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webvidhi.stocks.quotes.model.GlobalQuote;
import com.webvidhi.stocks.quotes.model.TradierPojo;
import com.webvidhi.stocks.quotes.model.TradierQuotes;
import com.webvidhi.stocks.quotes.query.QuoteQueryDispatcher;

@EnableCaching
@CrossOrigin
@RestController
@RequestMapping("/v2")
public class QuotesControllerV2 {

	@Autowired
	QuoteQueryDispatcher dispatcher;
	
	@GetMapping("quotes/{symbol}")
	public GlobalQuote getQoutes(@PathVariable String symbol) {
		
		return   dispatcher.getQouteInformationV2(symbol);
	}
	
}
