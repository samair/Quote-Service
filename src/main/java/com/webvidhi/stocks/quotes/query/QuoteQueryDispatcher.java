package com.webvidhi.stocks.quotes.query;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webvidhi.stocks.quotes.model.BestMatchSymbol;
import com.webvidhi.stocks.quotes.model.GlobalQuote;
import com.webvidhi.stocks.quotes.model.TradierPojo;
import com.webvidhi.stocks.quotes.model.TradierQuotes;



// Class sends REST query to fetch Qoute information on a 
// real time basis

@Service
public class QuoteQueryDispatcher {
	
	@Resource
	AlphaQueryDispatcher endpoint;
	
	@Resource
	TradierDispatcher tradierEndpoint;

	public GlobalQuote getQouteInformation(String symbol) {
		
		
		return endpoint.getQouteInfromation(symbol);
		
		
	}

	/*
	 * public List<BestMatchSymbol> getSymbolNames(String searchKey) {
	 * 
	 * 
	 * return endpoint.serachSymbol(searchKey);
	 * 
	 * 
	 * }
	 */
	public GlobalQuote getQouteInformationV2(String symbol) {
		
		
		return tradierEndpoint.getQouteInfromation(symbol);
		
		
	}
	public Object getSymbolNamesV2(String searchKey) {
		
		
		return tradierEndpoint.serachSymbol(searchKey);
		
		
	}
}

