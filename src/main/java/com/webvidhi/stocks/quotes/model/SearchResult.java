package com.webvidhi.stocks.quotes.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"bestMatches"
})
public class SearchResult {

@JsonProperty("bestMatches")
private List<BestMatchSymbol> bestMatches = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/*
@JsonAlias({"bestMatches","security"})
public List<BestMatchSymbol> getBestMatches() {
return bestMatches;
}*/

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}


}
