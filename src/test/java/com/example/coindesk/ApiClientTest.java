package com.example.coindesk;


import com.example.coindesk.back.bean.CoinName;
import com.example.coindesk.back.bean.response.CoinDeskRawResponse;
import com.example.coindesk.back.bean.response.GenerateCoinDeskInfo;
import com.example.coindesk.back.client.ApiClient;
import com.example.coindesk.back.service.ICoinExchangeService;
import com.example.coindesk.back.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiClientTest {

    @Autowired
    private ApiClient client;

    @Autowired
    private ICoinExchangeService service;

    final String coinDeskApi= "https://api.coindesk.com/v1/bpi/currentprice.json";

    @Test
    public void call_coinDeskApi_should_success_and_print_res(){

        Assertions.assertDoesNotThrow(
                ()->{
                    CoinDeskRawResponse res = client.sendGet(coinDeskApi, CoinDeskRawResponse.class);
                    System.out.println(JsonUtil.pojoToJson(res));
                }
        );

    }

    @Test
    public void call_coinExchangeApi_should_success_and_print_res() throws Exception {

        Assertions.assertDoesNotThrow(
                ()->{
                    GenerateCoinDeskInfo res = service.getCoinExchangeInfo();
                    System.out.println(JsonUtil.pojoToJson(res));
                }
        );

    }



}
