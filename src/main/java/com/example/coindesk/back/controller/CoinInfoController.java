package com.example.coindesk.back.controller;

import com.example.coindesk.back.bean.CoinName;
import com.example.coindesk.back.bean.request.CoinNameReq;
import com.example.coindesk.back.bean.response.CommonResponse;
import com.example.coindesk.back.service.ICoinNameServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/coins")
public class CoinInfoController {

    @Autowired
    ICoinNameServie coinNameService;

    @GetMapping("/coinNames/{id}")
    public CommonResponse<CoinName> find(@PathVariable(value = "id") Integer id) {
        return new CommonResponse(coinNameService.findById(id));
    }

    @GetMapping("/coinNames")
    public List<CoinName> findAll() {
        return coinNameService.findAll();
    }

    @GetMapping("/coinNamesMap")
    public Map<String, CoinName> findMap() {
        return coinNameService.getCoinEnNameMap(coinNameService.findAll());
    }

    @PostMapping("/coinNames")
    public CoinName insert(@RequestBody CoinNameReq req) {
        return coinNameService.insert(req);
    }

    @PutMapping("/coinNames")
    public CoinName update(@RequestBody CoinNameReq req) throws Exception {
        if (req.getId() == null) {
            throw new Exception();
        }
        return coinNameService.update(req);
    }

    @DeleteMapping("/coinNames/{id}")
    public int delete(@PathVariable(value = "id") Integer id) throws Exception {

        // todo: check for count correct
        int count = coinNameService.deleteById(id);

        return count;
    }


}
