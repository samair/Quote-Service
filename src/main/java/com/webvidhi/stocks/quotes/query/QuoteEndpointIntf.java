package com.webvidhi.stocks.quotes.query;


public interface QuoteEndpointIntf {
	

	public Object getQouteInfromation(String symbol);
	
	public Object serachSymbol(String searchKey);

}
