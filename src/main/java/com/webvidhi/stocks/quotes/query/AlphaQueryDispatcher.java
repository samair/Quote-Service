package com.webvidhi.stocks.quotes.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.webvidhi.stocks.quotes.model.AlphaQuoteResponse;
import com.webvidhi.stocks.quotes.model.GlobalQuote;

@Service
public class AlphaQueryDispatcher implements QuoteEndpointIntf {

	public AlphaQueryDispatcher() {
		super();
		
		this.logger = LoggerFactory.getLogger(AlphaQueryDispatcher.class);

		this.restTemplate = new RestTemplate();
	}

	Logger logger;

	RestTemplate restTemplate;

	String url;

	@Override
	public GlobalQuote getQouteInfromation(String symbol) {

		restTemplate = new RestTemplate();
		this.url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=";
		this.url += symbol;
		this.url += "&apikey=G5CXA85LO0ZJJ9PV";
		// AlphaQuteResponse response =
		ResponseEntity<AlphaQuoteResponse> response = restTemplate.getForEntity(url, AlphaQuoteResponse.class);

		GlobalQuote quote = new GlobalQuote();
		
        HttpStatus status = response.getStatusCode();
		if (HttpStatus.OK == status && (null == response.getBody().getGlobalQuote() || (null == response.getBody().getGlobalQuote().get01Symbol())))
	    {
			quote.setInvalidSymbol(true);
			logger.error("Invalid Symbol : "+ quote.isInvalidSymbol());
			
		}
		else if (HttpStatus.OK != status) {
			quote.setError(true);
			logger.error("HTTP response status : "+ quote.isError() );
			
		}
		else {
			quote=response.getBody().getGlobalQuote();
		}
		return quote;
	}

}
