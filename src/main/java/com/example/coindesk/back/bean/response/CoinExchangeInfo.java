package com.example.coindesk.back.bean.response;

import com.example.coindesk.back.bean.CoinInfoFromDesk;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinExchangeInfo {
    String chName;

    String enName;
    String exchangeRate;

}


