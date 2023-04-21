package com.example.coindesk.back.bean;

import com.example.coindesk.back.bean.response.CoinExchangeInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class CoinInfoFromDesk {
    @JsonProperty(value = "code")
    private String code;
    @JsonProperty(value = "symbol")
    private String symbol;
    @JsonProperty(value = "rate")
    private String rate;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "rate_float")
    private BigDecimal rate_float;

    public CoinExchangeInfo from(){
        CoinExchangeInfo res=new CoinExchangeInfo();
        res.setEnName(this.code);
        res.setExchangeRate(this.getRate());
        return res;
    }
}
