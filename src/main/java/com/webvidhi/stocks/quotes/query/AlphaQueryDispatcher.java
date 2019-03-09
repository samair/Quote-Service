package com.webvidhi.stocks.quotes.query;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.webvidhi.stocks.quotes.model.AlphaQuoteResponse;
import com.webvidhi.stocks.quotes.model.BestMatchSymbol;
import com.webvidhi.stocks.quotes.model.GlobalQuote;
import com.webvidhi.stocks.quotes.model.SearchResult;


@Service
public class AlphaQueryDispatcher implements QuoteEndpointIntf {
	
	enum queryType{
		Qoute,Search
	};

	public AlphaQueryDispatcher() {
		super();
		
		this.logger = LoggerFactory.getLogger(AlphaQueryDispatcher.class);

		this.restTemplate = new RestTemplate();
	}

	private Logger logger;

	private RestTemplate restTemplate;
	
	@Value("${alpha.symbolQuery:Default value}")
	private String url;
	
	@Value("${alpha.apikey}")
	private String apiKey;
	
    @Value("${alpha.Quote}")
	private String quoteQuery;
    
    @Value("${alpha.Search}")
    private String searchQuery;

    private String createURL(queryType type,String key){
    	logger.error("Base URL  : "+ this.url );
    	String url = this.url;
    	
    	url += (type == queryType.Qoute) ? (quoteQuery) : (searchQuery);

		url += key;
		url += "&apikey="+apiKey;
		
    	logger.error("Complete URL  : "+ url );
    	return url;
    }

	@Override
	public GlobalQuote getQouteInfromation(String symbol) {

	
		//restTemplate = new RestTemplate();
		String url = createURL(queryType.Qoute,symbol);
		
		logger.error("URL created is :" + url);


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


	@Override
	
	public List<BestMatchSymbol> serachSymbol (String searchKey) {
		
		String url = createURL(queryType.Search,searchKey);
		ResponseEntity<SearchResult> response = restTemplate.getForEntity(url, SearchResult.class);

		
        HttpStatus status = response.getStatusCode();
        
        logger.error("URL : "+ url+"serachSymbol HTTP response status : "+ status);
        
        if (HttpStatus.OK == status){
        	
        	logger.error("Found Entries " + response.getBody().getBestMatches().size());   

        	return response.getBody().getBestMatches();
        
        }
		return Collections.emptyList();
	}

}
