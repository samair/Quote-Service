package com.webvidhi.stocks.quotes.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"1. symbol",
"2. name",
"3. type",
"4. region",
"5. marketOpen",
"6. marketClose",
"7. timezone",
"8. currency",
"9. matchScore"
})
public class BestMatchSymbol implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@JsonProperty("1. symbol")
private String _1Symbol;
@JsonProperty("2. name")
private String _2Name;
@JsonProperty("3. type")
private String _3Type;
@JsonProperty("4. region")
private String _4Region;
@JsonProperty("5. marketOpen")
private String _5MarketOpen;
@JsonProperty("6. marketClose")
private String _6MarketClose;
@JsonProperty("7. timezone")
private String _7Timezone;
@JsonProperty("8. currency")
private String _8Currency;
@JsonProperty("9. matchScore")
private String _9MatchScore;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("1. symbol")
public String get1Symbol() {
return _1Symbol;
}

@JsonProperty("1. symbol")
public void set1Symbol(String _1Symbol) {
this._1Symbol = _1Symbol;
}

@JsonProperty("2. name")
public String get2Name() {
return _2Name;
}

@JsonProperty("2. name")
public void set2Name(String _2Name) {
this._2Name = _2Name;
}

@JsonProperty("3. type")
public String get3Type() {
return _3Type;
}

@JsonProperty("3. type")
public void set3Type(String _3Type) {
this._3Type = _3Type;
}

@JsonProperty("4. region")
public String get4Region() {
return _4Region;
}

@JsonProperty("4. region")
public void set4Region(String _4Region) {
this._4Region = _4Region;
}

@JsonProperty("5. marketOpen")
public String get5MarketOpen() {
return _5MarketOpen;
}

@JsonProperty("5. marketOpen")
public void set5MarketOpen(String _5MarketOpen) {
this._5MarketOpen = _5MarketOpen;
}

@JsonProperty("6. marketClose")
public String get6MarketClose() {
return _6MarketClose;
}

@JsonProperty("6. marketClose")
public void set6MarketClose(String _6MarketClose) {
this._6MarketClose = _6MarketClose;
}

@JsonProperty("7. timezone")
public String get7Timezone() {
return _7Timezone;
}

@JsonProperty("7. timezone")
public void set7Timezone(String _7Timezone) {
this._7Timezone = _7Timezone;
}

@JsonProperty("8. currency")
public String get8Currency() {
return _8Currency;
}

@JsonProperty("8. currency")
public void set8Currency(String _8Currency) {
this._8Currency = _8Currency;
}

@JsonProperty("9. matchScore")
public String get9MatchScore() {
return _9MatchScore;
}

@JsonProperty("9. matchScore")
public void set9MatchScore(String _9MatchScore) {
this._9MatchScore = _9MatchScore;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}