package com.webvidhi.stocks.quotes.model;

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
"01. symbol",
"02. open",
"03. high",
"04. low",
"05. price",
"06. volume",
"07. latest trading day",
"08. previous close",
"09. change",
"10. change percent"
})
public class GlobalQuote {

@JsonProperty("01. symbol")
private String _01Symbol;
@JsonProperty("02. open")
private String _02Open;
@JsonProperty("03. high")
private String _03High;
@JsonProperty("04. low")
private String _04Low;
@JsonProperty("05. price")
private String _05Price;
@JsonProperty("06. volume")
private String _06Volume;
@JsonProperty("07. latest trading day")
private String _07LatestTradingDay;
@JsonProperty("08. previous close")
private String _08PreviousClose;
@JsonProperty("09. change")
private String _09Change;
@JsonProperty("10. change percent")
private String _10ChangePercent;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("01. symbol")
public String get01Symbol() {
return _01Symbol;
}

@JsonProperty("01. symbol")
public void set01Symbol(String _01Symbol) {
this._01Symbol = _01Symbol;
}

@JsonProperty("02. open")
public String get02Open() {
return _02Open;
}

@JsonProperty("02. open")
public void set02Open(String _02Open) {
this._02Open = _02Open;
}

@JsonProperty("03. high")
public String get03High() {
return _03High;
}

@JsonProperty("03. high")
public void set03High(String _03High) {
this._03High = _03High;
}

@JsonProperty("04. low")
public String get04Low() {
return _04Low;
}

@JsonProperty("04. low")
public void set04Low(String _04Low) {
this._04Low = _04Low;
}

@JsonProperty("05. price")
public String get05Price() {
return _05Price;
}

@JsonProperty("05. price")
public void set05Price(String _05Price) {
this._05Price = _05Price;
}

@JsonProperty("06. volume")
public String get06Volume() {
return _06Volume;
}

@JsonProperty("06. volume")
public void set06Volume(String _06Volume) {
this._06Volume = _06Volume;
}

@JsonProperty("07. latest trading day")
public String get07LatestTradingDay() {
return _07LatestTradingDay;
}

@JsonProperty("07. latest trading day")
public void set07LatestTradingDay(String _07LatestTradingDay) {
this._07LatestTradingDay = _07LatestTradingDay;
}

@JsonProperty("08. previous close")
public String get08PreviousClose() {
return _08PreviousClose;
}

@JsonProperty("08. previous close")
public void set08PreviousClose(String _08PreviousClose) {
this._08PreviousClose = _08PreviousClose;
}

@JsonProperty("09. change")
public String get09Change() {
return _09Change;
}

@JsonProperty("09. change")
public void set09Change(String _09Change) {
this._09Change = _09Change;
}

@JsonProperty("10. change percent")
public String get10ChangePercent() {
return _10ChangePercent;
}

@JsonProperty("10. change percent")
public void set10ChangePercent(String _10ChangePercent) {
this._10ChangePercent = _10ChangePercent;
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

