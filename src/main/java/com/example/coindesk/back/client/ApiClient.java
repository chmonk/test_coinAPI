package com.example.coindesk.back.client;

import com.example.coindesk.back.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

@Service
public class ApiClient {

    @Autowired
    RestTemplate restTemplate;

    public <T> T sendGet(String uri, Class<T> type) {

        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, getDefaultEntity(), String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            T res = JsonUtil.stringToPojo(responseEntity.getBody(), type);
            return res;
        } else {
            //todo: error log
            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());
            return null;
        }
    }

    private HttpEntity<String> getDefaultEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return entity;
    }

}
