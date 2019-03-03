package com.webvidhi.stocks.quotes.query;

import org.springframework.stereotype.Service;



// Class sends REST query to fetch Qoute information on a 
// reltime basis

@Service
public class QuoteQueryDispatcher {
	
	

	public static String getQouteInformation(String symbol) {
		
		QuoteEndpointIntf endpoint = new AlphaQueryDispatcher();
		
		return (String) endpoint.getQouteInfromation(symbol);
		
		
	}
}
