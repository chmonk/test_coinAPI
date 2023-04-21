package com.example.coindesk.back.bean.response;

import com.example.coindesk.back.bean.CoinInfoFromDesk;
import com.example.coindesk.back.bean.TimeTag;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class CoinDeskRawResponse {

    @JsonProperty(value = "time")
    TimeTag time;
    @JsonProperty(value = "disclaimer")
    String disclaimer ;

    @JsonProperty(value = "chartName")
    String chartName ;

    @JsonProperty(value = "bpi")
    Map<String, CoinInfoFromDesk> bpi;

}


