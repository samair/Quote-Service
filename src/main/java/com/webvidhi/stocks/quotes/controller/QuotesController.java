package com.webvidhi.stocks.quotes.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
@RequestMapping("/")
public class QuotesController {
	
	@Autowired
	QuoteQueryDispatcher dispatcher;
	
	@GetMapping("quotes/{symbol}")
	public GlobalQuote getQoutes(@PathVariable String symbol) {
		
		return   dispatcher.getQouteInformation(symbol);
	}
	
	@GetMapping("search/{searchKey}")
	//@Cacheable("search")
	public Object searchSymbols(@PathVariable String searchKey) {
		
		return   dispatcher.getSymbolNamesV2(searchKey);
	}

}
