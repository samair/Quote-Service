package com.webvidhi.stocks.quotes.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.webvidhi.stocks.quotes.model.AlphaQuoteResponse;

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
	public String getQouteInfromation(String symbol) {
		
		String symbolValue;

		restTemplate = new RestTemplate();
		this.url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=";
		this.url += symbol;
		this.url += "&apikey=G5CXA85LO0ZJJ9PV";
		// AlphaQuteResponse response =
		ResponseEntity<AlphaQuoteResponse> response = restTemplate.getForEntity(url, AlphaQuoteResponse.class);

	
        HttpStatus status = response.getStatusCode();
		if (HttpStatus.OK == status && (null == response.getBody().getGlobalQuote()
				|| null == response.getBody().getGlobalQuote().get05Price())) {
			symbolValue = "Not Found";
		}
		else if (HttpStatus.OK != status) {
			logger.info("Query response status "+ status);
			symbolValue = "Error";
		}
		else {
			symbolValue=response.getBody().getGlobalQuote().get05Price();
		}
		return symbolValue;
	}

}
