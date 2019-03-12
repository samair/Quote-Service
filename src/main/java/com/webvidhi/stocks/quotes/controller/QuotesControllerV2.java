package com.webvidhi.stocks.quotes.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webvidhi.stocks.quotes.model.BestMatchSymbol;
import com.webvidhi.stocks.quotes.model.GlobalQuote;
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
	
	@GetMapping("search/{searchKey}")
	//@Cacheable("search")
	public Object searchSymbols(@PathVariable String searchKey) {
		
		return   dispatcher.getSymbolNamesV2(searchKey);
	}

}
