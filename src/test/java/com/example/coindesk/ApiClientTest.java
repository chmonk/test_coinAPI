package com.example.coindesk;


import com.example.coindesk.back.bean.response.CoinDeskRawResponse;
import com.example.coindesk.back.bean.response.GenerateCoinDeskInfo;
import com.example.coindesk.back.client.ApiClient;
import com.example.coindesk.back.service.ICoinExchangeService;
import com.example.coindesk.back.util.JsonUtil;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiClientTest {

    @Autowired
    private ApiClient client;

    @Autowired
    private ICoinExchangeService service;

    @Autowired
    private Environment env;

    @Test
    public void call_coinDeskApi_should_success_and_print_res(){

        String coinDeskUri = env.getProperty("constant.api.coindeskApi");

        Assertions.assertDoesNotThrow(
                ()->{
                    CoinDeskRawResponse res = client.sendGet(coinDeskUri, CoinDeskRawResponse.class);
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
