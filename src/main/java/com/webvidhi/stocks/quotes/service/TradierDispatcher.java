package com.webvidhi.stocks.quotes.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.webvidhi.stocks.quotes.model.AlphaQuoteResponse;
import com.webvidhi.stocks.quotes.model.BestMatchSymbol;
import com.webvidhi.stocks.quotes.model.GlobalQuote;
import com.webvidhi.stocks.quotes.model.SearchResult;
import com.webvidhi.stocks.quotes.model.SymbolSearch;
import com.webvidhi.stocks.quotes.model.TradierPojo;
import com.webvidhi.stocks.quotes.model.TradierQuotes;
import com.webvidhi.stocks.quotes.service.AlphaQueryDispatcher.queryType;

@Service
public class TradierDispatcher implements QuoteEndpointIntf {


	enum queryType{
		Qoute,Search
	};

	public TradierDispatcher() {
		super();
		
		this.logger = LoggerFactory.getLogger(TradierDispatcher.class);

		this.restTemplate = new RestTemplate();
	}

	private Logger logger;

	private RestTemplate restTemplate;
	
	@Value("${tradier.marketQuery:Default value}")
	private String url;
	
	@Value("${tradier.apikey}")
	private String apiKey;
	
    @Value("${tradier.Quote}")
	private String quoteQuery;
    
    @Value("${tradier.Search}")
    private String searchQuery;

    private String createURL(queryType type,String key){
    	logger.error("Base URL  : "+ this.url );
    	String url = this.url;
    	
    	url += (type == queryType.Qoute) ? (quoteQuery) : (searchQuery);

		url += key;
	//	url += "&apikey="+apiKey;
		
    	logger.error("Complete URL  : "+ url );
    	return url;
    }

	@Override
	public TradierPojo getQouteInfromation(String symbol) {

	
		//restTemplate = new RestTemplate();
		String url = createURL(queryType.Qoute,symbol);
		//String url = "https://sandbox.tradier.com/v1/markets/quotes?symbols="+symbol;
		
		logger.error("URL created is :" + url);
			
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(apiKey);

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<TradierPojo> response = restTemplate.exchange(url,HttpMethod.GET, entity,TradierPojo.class);
		
		TradierQuotes quote = null;

		boolean isError = false;
		
        HttpStatus status = response.getStatusCode();
		if (HttpStatus.OK == status && (true == response.getBody().getQuotes().unmatchedSymbol()))
	    {
			logger.error("Invalid Symbol !!");
			response.getBody().getQuotes().setInvalidSymbol(true);
			
		}
		else if (HttpStatus.OK != status) {
			isError = true;
			
			logger.error("HTTP response status : " + status);
			
		}
		
		return response.getBody();
	}


	@Override
	
	public Object serachSymbol (String searchKey) {
		

		String url = createURL(queryType.Search,searchKey);
		logger.debug("URL created is :" + url);
			
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(apiKey);
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		
		ResponseEntity<SymbolSearch> response = restTemplate.exchange(url,HttpMethod.GET, entity,SymbolSearch.class);

		
        HttpStatus status = response.getStatusCode();
        
        logger.debug("URL : "+ url+" serachSymbol HTTP response status : "+ status);
        
        if (HttpStatus.OK == status && null!= response.getBody().getResults()){
        	
        	logger.debug("Found Entries ");   
        	
        	// Adding search-key so that every resoponse has information about what was
        	// the search key
        	response.getBody().getResults().setAdditionalProperty("search-key", searchKey);
        	return response.getBody().getResults().getAdditionalProperties();
        
        }
        else  if (null == response.getBody().getResults()){
        	
        	return  response.getBody();
        	
        }
		return Collections.emptyMap();
	}

	public TradierPojo getMultiQuoteInfo(List<String> symbols) {
		// restTemplate = new RestTemplate();
		String url = createURL(queryType.Qoute, symbols);
		// String url = "https://sandbox.tradier.com/v1/markets/quotes?symbols="+symbol;

		logger.error("URL created is :" + url);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(apiKey);

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<TradierPojo> response = restTemplate.exchange(url, HttpMethod.GET, entity, TradierPojo.class);

		boolean isInvalid = false;
		boolean isError = false;

		HttpStatus status = response.getStatusCode();
		if (HttpStatus.OK == status && (null == response.getBody())) {
			isInvalid = true;
			logger.error("Invalid Symbol : " + isInvalid);

		} else if (HttpStatus.OK != status) {
			isError = true;
			logger.error("HTTP response status : " + status);
		}

		return response.getBody();
	}

	private String createURL(queryType qoute, List<String> symbols) {
    	logger.error("Base URL  : "+ this.url );
    	String url = this.url;
    	
    	url += (qoute == queryType.Qoute) ? (quoteQuery) : (searchQuery);

    	url +=String.join(",", symbols);

		
    	logger.error("Complete URL  : "+ url );
    	return url;
		
	}

}
