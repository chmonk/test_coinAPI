package com.example.coindesk.back.controller;

import com.example.coindesk.back.bean.response.CommonResponse;
import com.example.coindesk.back.bean.response.GenerateCoinDeskInfo;
import com.example.coindesk.back.service.ICoinExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.example.coindesk.back.bean.response.CommonResponse.setCurrentDate;

@RestController
@RequestMapping("/coinExchange")
public class CoinExchangeController {

    @Autowired
    ICoinExchangeService coinExchangeService;

    @GetMapping("/getCoinExchangeRates")
    public CommonResponse<GenerateCoinDeskInfo> get() throws Exception {
        return setCurrentDate(new CommonResponse(coinExchangeService.getCoinExchangeInfo()));
    }
}
