package com.webvidhi.stocks.quotes.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonIgnoreProperties({"bid","week_52_high","change”,“prevclose”,“week_52_low”,“asksize","ask_date","type","trade_date”,“volume","last_volume","change_percentage", "bidexch", "average_volume”,“exch","bidsize”,“ask","askexch","close","bid_date"})

@JsonInclude(JsonInclude.Include.NON_NULL)

public class GlobalQuote {

public GlobalQuote() {

	}


private String Symbol;

private String Open;

private String High;

private String Low;

private String Price;

private boolean error;

private boolean invalidSymbol;

@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("01. symbol")
public String get01Symbol() {
return Symbol;
}

@JsonAlias({"01. symbol","symbol"})
public void set01Symbol(String _01Symbol) {
this.Symbol = _01Symbol;
}

@JsonProperty("02. open")
public String get02Open() {
return Open;
}

@JsonAlias({"02. open","open"})
public void set02Open(String _02Open) {
this.Open = _02Open;
}

@JsonProperty("03. high")
public String get03High() {
return High;
}

@JsonAlias({"03. high","high"})
public void set03High(String _03High) {
this.High = _03High;
}

@JsonProperty("04. low")
public String get04Low() {
return Low;
}

@JsonAlias({"04. low", "low"})
public void set04Low(String _04Low) {
this.Low = _04Low;
}

@JsonProperty("05. price")
public String get05Price() {
return Price;
}

@JsonAlias({"05. price","last"})
public void set05Price(String _05Price) {
this.Price = _05Price;
}

public boolean isError() {
	return error;
}

public void setError(boolean error) {
	this.error = error;
}

public boolean isInvalidSymbol() {
	return invalidSymbol;
	
}

public void setInvalidSymbol(boolean invalSym) {
	this.invalidSymbol = invalSym;
	
}


}

