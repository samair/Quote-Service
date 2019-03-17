package com.webvidhi.stocks.quotes.service;


public interface QuoteEndpointIntf {
	

	public Object getQouteInfromation(String symbol);
	
	public Object serachSymbol(String searchKey);

}
