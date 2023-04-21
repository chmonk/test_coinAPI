package com.example.coindesk.back.controller;

import com.example.coindesk.back.bean.CoinInfoFromDesk;
import com.example.coindesk.back.bean.CoinName;
import com.example.coindesk.back.bean.response.CoinDeskRawResponse;
import com.example.coindesk.back.bean.response.CoinExchangeInfo;
import com.example.coindesk.back.bean.response.RegenCoinDeskInfo;
import com.example.coindesk.back.service.ICoinNameServie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/coinExchange")
public class CoinExchangeController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ICoinNameServie coinNameService;

    @GetMapping("/getCoinExchangeRates")
    public RegenCoinDeskInfo get() throws IOException {

        // todo: pull as abstract
        String uri = "https://api.coindesk.com/v1/bpi/currentprice.json";

        // todo: package as util
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        CoinDeskRawResponse res = (new ObjectMapper()).readValue(responseEntity.getBody(), CoinDeskRawResponse.class);

        DateTime time = new DateTime(res.getTime().updatedISO);

        DateTimeFormatter a = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");

        String newTime = a.print(time);

        RegenCoinDeskInfo ress = new RegenCoinDeskInfo();

        ress.setUpdateTime(newTime);

        Map<String, CoinName> tempMap = coinNameService.getCoinEnNameMap(coinNameService.findAll());

        Map<String, CoinExchangeInfo> map = res.getBpi().entrySet().stream().map((entry) -> {
            String enName = entry.getKey();
            CoinInfoFromDesk info = entry.getValue();
            CoinExchangeInfo r = info.from();
            String chName = Optional.ofNullable(tempMap.get(enName)).map(CoinName::getChName).orElse("");
            r.setEnName(enName);
            r.setChName(chName);
            return r;
        }).collect(Collectors.toMap(CoinExchangeInfo::getEnName, Function.identity()));

        ress.setInfo(map);

        return ress;

    }
}
