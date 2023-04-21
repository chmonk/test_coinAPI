package com.example.coindesk.back.controller;

import com.example.coindesk.back.bean.response.GenerateCoinDeskInfo;
import com.example.coindesk.back.client.ApiClient;
import com.example.coindesk.back.service.CoinExchangeService;
import com.example.coindesk.back.service.ICoinNameServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/coinExchange")
public class CoinExchangeController {

    @Autowired
    CoinExchangeService coinExchangeService;

    @GetMapping("/getCoinExchangeRates")
    public GenerateCoinDeskInfo get() throws IOException {
        return coinExchangeService.getCoinExchangeInfo();

    }
}
