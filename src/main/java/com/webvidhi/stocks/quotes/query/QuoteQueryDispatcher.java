package com.webvidhi.stocks.quotes.query;

import org.springframework.stereotype.Service;

import com.webvidhi.stocks.quotes.model.GlobalQuote;



// Class sends REST query to fetch Qoute information on a 
// real time basis

@Service
public class QuoteQueryDispatcher {
	
	

	public static GlobalQuote getQouteInformation(String symbol) {
		
		QuoteEndpointIntf endpoint = new AlphaQueryDispatcher();
		
		return (GlobalQuote) endpoint.getQouteInfromation(symbol);
		
		
	}
}
