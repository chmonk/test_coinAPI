package com.example.coindesk;


import com.example.coindesk.back.bean.CoinName;
import com.example.coindesk.back.bean.response.CoinDeskRawResponse;
import com.example.coindesk.back.bean.response.GenerateCoinDeskInfo;
import com.example.coindesk.back.client.ApiClient;
import com.example.coindesk.back.service.ICoinExchangeService;
import com.example.coindesk.back.service.ICoinNameService;
import com.example.coindesk.back.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoinExchangeServiceTest {

    @Autowired
    private ICoinExchangeService coinExchangeService;

    @MockBean
    private ICoinNameService mockCoinNameService;

    @MockBean
    private ApiClient mockClient;

    @Value("classpath:coinDeskRes.json")
    private Resource coinDeskResFile;

    @Value("classpath:test.json")
    private Resource testFile;

    @Test
    public void coinInfoCoinNum_equal_coinNumFromCoinDeskAPI() throws Exception {

        // mock
        CoinDeskRawResponse apiRes = JsonUtil.resourceToPojo(coinDeskResFile,CoinDeskRawResponse.class);
        Mockito.when(mockClient.sendGet(any(),any())).thenReturn(apiRes);
        List<CoinName> coinNameList = JsonUtil.resourceToPojo(testFile, new TypeReference<>() {});
        Mockito.when(mockCoinNameService.findAll()).thenReturn(coinNameList);

        GenerateCoinDeskInfo testResult = coinExchangeService.getCoinExchangeInfo();

        int replaceNum = testResult.getInfo().entrySet().size();
        int correctNum = apiRes.getBpi().entrySet().size();

        Assert.assertEquals(correctNum,replaceNum);

    }






}
