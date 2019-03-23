package com.webvidhi.stocks.quotes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webvidhi.stocks.quotes.model.SessionMessage;
import com.webvidhi.stocks.quotes.model.SessionSubscription;
import com.webvidhi.stocks.quotes.model.SymbolSearch;
import com.webvidhi.stocks.quotes.model.TradierPojo;
import com.webvidhi.stocks.quotes.model.TradierQuotes;
import com.webvidhi.stocks.quotes.service.QuoteQueryDispatcher;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@EnableCaching
@CrossOrigin
@RestController
@RequestMapping("/v2")
@Api(value="Quotes Controller")
public class QuotesControllerV2 {

	@Autowired
	QuoteQueryDispatcher dispatcher;
	
	@Autowired
	SessionSubscription subscriber;
	
	@GetMapping("quotes/{symbol}")
	@ApiOperation(value = "Lists quote infromation of a stock's symbol", response = TradierPojo.class)
	public TradierPojo getQoutes(@PathVariable(required = false) String symbol) {
		
		if(null == symbol)
		{
			TradierPojo quoteObj = new TradierPojo();
			quoteObj.getQuotes().setError(true);
			return quoteObj;
		}
		return   dispatcher.getQouteInformationV2(symbol);
	}
	
	@GetMapping("search/{searchKey}")
	@ApiOperation(value = "Lists symbols for a given search key", response = SymbolSearch.class)
	public Object searchSymbols(@PathVariable String searchKey) {
		
		return   dispatcher.getSymbolNamesV2(searchKey);
	}
	
	@PostMapping("session/stream")
	@ApiOperation(value = "Generate a stream session ID", response = String.class)
	public SessionMessage generateSessionId() {
		
		//Generate a session Id and send it to user.
		String uuid = UUID.randomUUID().toString().replace("-", "");
	    System.out.println("uuid = " + uuid);
	    
	    SessionMessage sessionMsg = new SessionMessage();
	    sessionMsg.setSessionId("1234");
	    List<String> symbols = new ArrayList<>();
	    symbols.add("AAA");
	    symbols.add("BBB");
	    		sessionMsg.setSymbols(symbols);
		return sessionMsg;
		
	}
	
	@PostMapping("stream")
	@ApiOperation(value = "Create a Stream session using sessionID ", response = String.class)
	public String createStreamSession(@RequestParam String sessionId, @RequestParam List<String> symbols) {
		subscriber.addSubscription(sessionId, symbols);
		
		return "OK";
		
	}
	
	@GetMapping("quotes")
	@ApiOperation(value = "Lists quote infromation for one or more stock symbols", response = String.class)
	public TradierPojo getQuotes(@RequestParam(required = false) List<String> symbols) {
		
		if(null == symbols || symbols.isEmpty())
		{
			TradierPojo quoteObj = new TradierPojo();
			TradierQuotes q = new TradierQuotes();
			quoteObj.setQuotes(q);
			quoteObj.getQuotes().setError(true);
			return quoteObj;
		}
		return dispatcher.getQouteInfoMulti(symbols);
	}

}
