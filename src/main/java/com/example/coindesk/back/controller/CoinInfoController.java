package com.example.coindesk.back.controller;

import com.example.coindesk.back.Enum.StatusEnum;
import com.example.coindesk.back.bean.CoinName;
import com.example.coindesk.back.bean.request.CoinNameReq;
import com.example.coindesk.back.bean.response.CommonResponse;
import com.example.coindesk.back.service.ICoinNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.example.coindesk.back.bean.response.CommonResponse.setCurrentDate;


@RestController
@RequestMapping("/coins")
public class CoinInfoController {

    @Autowired
    ICoinNameService coinNameService;

    @GetMapping("/coinNames/{id}")
    public CommonResponse<CoinName> find(@PathVariable(value = "id") Integer id) {
        return setCurrentDate(new CommonResponse(coinNameService.findById(id)));
    }

    @GetMapping("/coinNames")
    public CommonResponse<List<CoinName>> findAll() {
        return setCurrentDate(new CommonResponse(coinNameService.findAll()));
    }

    @GetMapping("/coinNamesMap")
    public CommonResponse<Map<String, CoinName>> findMap() {
        return setCurrentDate(new CommonResponse(coinNameService.getCoinEnNameMap(coinNameService.findAll())));
    }

    @PostMapping("/coinNames")
    public CommonResponse<CoinName> insert(@RequestBody CoinNameReq req) {
        return setCurrentDate(new CommonResponse(coinNameService.insert(req)));
    }

    @PutMapping("/coinNames")
    public CommonResponse<CoinName> update(@RequestBody CoinNameReq req) throws Exception {

        CommonResponse<CoinName> res;
        if (req.getId() == null) {
            res = new CommonResponse<>(StatusEnum.FAIL, "id should not be empty.", null);
        } else if (coinNameService.findById(req.getId()) == null) {
            res = new CommonResponse<>(StatusEnum.FAIL, "data not exists.", null);
        } else {
            CoinName result = coinNameService.update(req);
            res = new CommonResponse<>(result);
        }

        return setCurrentDate(res);
    }

    @DeleteMapping("/coinNames/{id}")
    public CommonResponse<Integer> delete(@PathVariable(value = "id") Integer id) {
        int count = coinNameService.deleteById(id);
        return setCurrentDate(new CommonResponse<>(count));
    }


}
