package com.webvidhi.stocks.quotes.query;

import java.util.Collections;
import java.util.List;

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
import com.webvidhi.stocks.quotes.query.AlphaQueryDispatcher.queryType;

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
	public GlobalQuote getQouteInfromation(String symbol) {

	
		//restTemplate = new RestTemplate();
		String url = createURL(queryType.Qoute,symbol);
		//String url = "https://sandbox.tradier.com/v1/markets/quotes?symbols="+symbol;
		
		logger.error("URL created is :" + url);
			
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(apiKey);

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<TradierPojo> response = restTemplate.exchange(url,HttpMethod.GET, entity,TradierPojo.class);
		
		GlobalQuote quote = null;
		boolean isInvalid = false;
		boolean isError = false;
		
        HttpStatus status = response.getStatusCode();
		if (HttpStatus.OK == status && (null == response.getBody() || null == response.getBody().getQuotes() || null == response.getBody().getQuotes().getQuote()))
	    {
			isInvalid = true;
			logger.error("Invalid Symbol : " + isInvalid);
			
		}
		else if (HttpStatus.OK != status) {
			isError = true;
			logger.error("HTTP response status : " + status);
			
		}
		else {
			quote=(GlobalQuote) response.getBody().getQuotes().getQuote();
			logger.debug("HTTP response status : " + status +" price : "+ quote.get05Price());
		}
		if (null == quote) {
			quote = new GlobalQuote();
			quote.setError(isError);
			quote.setInvalidSymbol(isInvalid);
		}
		
		return quote;
	}


	@Override
	
	public List<BestMatchSymbol> serachSymbol (String searchKey) {
		

		String url = createURL(queryType.Search,searchKey);
		logger.debug("URL created is :" + url);
			
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(apiKey);
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		
		ResponseEntity<SymbolSearch> response = restTemplate.exchange(url,HttpMethod.GET, entity,SymbolSearch.class);

		
        HttpStatus status = response.getStatusCode();
        
        logger.debug("URL : "+ url+" serachSymbol HTTP response status : "+ status);
        
        if (HttpStatus.OK == status){
        	
        	logger.debug("Found Entries " + response.getBody().getResults().getBestMatches().size());   

        	return response.getBody().getResults().getBestMatches();
        
        }
		return Collections.emptyList();
	}

}
