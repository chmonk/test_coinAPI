package com.example.coindesk.back.service;

import com.example.coindesk.back.bean.response.GenerateCoinDeskInfo;

import java.io.IOException;

public interface ICoinExchangeService {
    public GenerateCoinDeskInfo getCoinExchangeInfo() throws Exception;
}
