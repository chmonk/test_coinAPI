package com.example.coindesk.back.service;

import com.example.coindesk.back.bean.CoinName;
import com.example.coindesk.back.bean.request.CoinNameReq;

import java.util.List;
import java.util.Map;

public interface ICoinNameServie {

    List<CoinName> findAll();

    Map<String,CoinName> getCoinEnNameMap(List<CoinName> list);

    CoinName findById(Integer id);

    CoinName findByEn(String enName);

    CoinName update(CoinNameReq req);

    CoinName insert(CoinNameReq req);

    int deleteById(int id);
}
