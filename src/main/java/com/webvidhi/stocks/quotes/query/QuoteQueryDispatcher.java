package com.webvidhi.stocks.quotes.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webvidhi.stocks.quotes.model.BestMatchSymbol;
import com.webvidhi.stocks.quotes.model.GlobalQuote;



// Class sends REST query to fetch Qoute information on a 
// real time basis

@Service
public class QuoteQueryDispatcher {
	
	@Autowired
	AlphaQueryDispatcher endpoint;

	public GlobalQuote getQouteInformation(String symbol) {
		
		
		return (GlobalQuote) endpoint.getQouteInfromation(symbol);
		
		
	}
	public List<BestMatchSymbol> getSymbolNames(String searchKey) {
		
		
		return (List<BestMatchSymbol>) endpoint.serachSymbol(searchKey);
		
		
	}
}
