package com.example.coindesk.back.bean;

import com.example.coindesk.back.bean.response.CoinExchangeInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class CoinInfoFromDesk {
    @JsonProperty(value = "code")
    String code;
    @JsonProperty(value = "symbol")
    String symbol;
    @JsonProperty(value = "rate")
    String rate;
    @JsonProperty(value = "description")
    String description;
    @JsonProperty(value = "rate_float")
    BigDecimal rate_float;

    public CoinExchangeInfo from(){
        CoinExchangeInfo res=new CoinExchangeInfo();
        res.setChName(this.getCode());
        res.setExchangeRate(this.getRate());
        return res;
    }
}
