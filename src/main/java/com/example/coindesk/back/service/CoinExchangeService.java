package com.example.coindesk.back.service;


import com.example.coindesk.back.bean.CoinInfoFromDesk;
import com.example.coindesk.back.bean.CoinName;
import com.example.coindesk.back.bean.response.CoinDeskRawResponse;
import com.example.coindesk.back.bean.response.CoinExchangeInfo;
import com.example.coindesk.back.bean.response.GenerateCoinDeskInfo;
import com.example.coindesk.back.client.ApiClient;
import com.example.coindesk.back.util.DateUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CoinExchangeService implements ICoinExchangeService {

    private static final Logger logger = LoggerFactory.getLogger(CoinExchangeService.class);


    @Autowired
    ICoinNameService coinNameService;

    @Autowired
    ApiClient client;

    @Autowired
    private Environment env;

    public GenerateCoinDeskInfo getCoinExchangeInfo() throws Exception {

        String uri = env.getProperty("constant.api.coindeskApi");

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

        //log to trace the lost coinName
        if(chName.equals("")){
            logger.warn(String.format("db lost %1$s data", enName));
        }

        cMap.setChName(chName);
        return cMap;
    }


}
