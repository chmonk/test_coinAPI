package com.example.coindesk.back.client;

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

    static ObjectMapper obj= new ObjectMapper();

    public <T> T sendGet(String uri,Class<T> type) throws IOException {

        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, getDefaultEntity(), String.class);

        T res = obj.readValue(responseEntity.getBody(), type);

        return res;
    }

    private HttpEntity<String> getDefaultEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return entity;
    }

}
