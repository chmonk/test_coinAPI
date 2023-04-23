package com.example.coindesk.back.client;

import com.example.coindesk.back.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ApiClient {

    private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);

    @Autowired
    RestTemplate restTemplate;

    public <T> T sendGet(String uri, Class<T> type) {

        logger.info(String.format("send Get request to %1$s", uri));

        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, getDefaultEntity(), String.class);

        String responseStr = responseEntity.getBody();

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            logger.info(String.format("get %1$s response: %2$s", responseEntity.getStatusCode(), responseStr));
            T res = JsonUtil.stringToPojo(responseStr, type);
            return res;
        } else {
            logger.warn(String.format("get %1$s response: %2$s", responseEntity.getStatusCode(), responseStr));
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
