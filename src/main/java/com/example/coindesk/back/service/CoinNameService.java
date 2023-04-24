package com.example.coindesk.back.service;


import com.example.coindesk.back.bean.CoinName;
import com.example.coindesk.back.bean.request.CoinNameReq;
import com.example.coindesk.back.client.ApiClient;
import com.example.coindesk.back.dao.CoinNameRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class CoinNameService implements ICoinNameService {

    private static final Logger logger = LoggerFactory.getLogger(CoinNameService.class);


    @Autowired
    CoinNameRepository repository;

    @Autowired
    EntityManager manager;

    @Override
    public List<CoinName> findAll() {
        return repository.findAll();
    }

    @Override
    public Map<String, CoinName> getCoinEnNameMap(List<CoinName> list) {
        return list.stream().collect(Collectors.toMap(CoinName::getEnName, Function.identity()));
    }

    @Override
    public CoinName findById(Integer id) {

        CoinName res;
        if (id == null) {
            res = null;
        } else {
            res = repository.findById(id).orElse(null);
        }
        return res;
    }

    @Override
    public CoinName findByEn(String enName) {
        return Optional.ofNullable(repository.findByEnName(enName.toUpperCase())).orElse(null);
    }


    @Override
    public CoinName update(CoinNameReq req) {
        return repository.save(req.toCoinName());
    }

    @Override
    public CoinName insert(CoinNameReq req) {
        CoinName newData = req.toCoinName();
        repository.save(newData);
        return newData;
    }

    @Override
    public int deleteById(int id) {
        return repository.deleteWithId(id);
    }
}
