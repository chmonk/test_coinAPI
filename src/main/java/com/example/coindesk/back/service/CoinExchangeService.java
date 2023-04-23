package com.example.coindesk.back.service;


import com.example.coindesk.back.bean.CoinInfoFromDesk;
import com.example.coindesk.back.bean.CoinName;
import com.example.coindesk.back.bean.response.CoinDeskRawResponse;
import com.example.coindesk.back.bean.response.CoinExchangeInfo;
import com.example.coindesk.back.bean.response.GenerateCoinDeskInfo;
import com.example.coindesk.back.client.ApiClient;
import com.example.coindesk.back.util.DateUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CoinExchangeService implements ICoinExchangeService {

    @Autowired
    ICoinNameService coinNameService;

    @Autowired
    ApiClient client;

    public GenerateCoinDeskInfo getCoinExchangeInfo() throws Exception {

        String uri = "https://api.coindesk.com/v1/bpi/currentprice.json";

        CoinDeskRawResponse apiRes = client.sendGet(uri, CoinDeskRawResponse.class);

        GenerateCoinDeskInfo res = new GenerateCoinDeskInfo();

        // get format date
        DateTime time = DateUtil.dateFromISOstr(apiRes.getTime().updatedISO);
        String newTime = DateUtil.dateToString(time, DateUtil.yyyyMMdd_HHmmss);
        res.setUpdateTime(newTime);

        // get
        Map<String, CoinName> coinNameMap = coinNameService.getCoinEnNameMap(coinNameService.findAll());

        Map<String, CoinExchangeInfo> infoMap = apiRes.getBpi().entrySet().stream().map((entry) ->
                buildCoinExchangeInfo(entry, coinNameMap)).collect(Collectors.toMap(CoinExchangeInfo::getEnName, Function.identity()));

        res.setInfo(infoMap);

        return res;

    }

    private CoinExchangeInfo buildCoinExchangeInfo(Map.Entry<String, CoinInfoFromDesk> entry, Map<String, CoinName> coinNameMap) {
        String enName = entry.getKey();
        CoinInfoFromDesk info = entry.getValue();
        CoinExchangeInfo cMap = info.from();
        String chName = Optional.ofNullable(coinNameMap.get(enName)).map(CoinName::getChName).orElse("");
        cMap.setChName(chName);
        return cMap;
    }


}
