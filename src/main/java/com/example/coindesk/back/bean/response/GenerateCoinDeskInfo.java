package com.example.coindesk.back.bean.response;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class GenerateCoinDeskInfo {
    String updateTime;
    Map<String,CoinExchangeInfo> info;
}


