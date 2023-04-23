package com.example.coindesk.back.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;

public class JsonUtil {

    static ObjectMapper obj= new ObjectMapper();

    public static  <T> T stringToPojo (String jsonStr, Class<T> type){
        try{
            T pojo = obj.readValue(jsonStr,type);
            return pojo;
        }catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }

    public static <T> String pojoToJson (T pojo) throws JsonProcessingException {
        try{
            String str = obj.writeValueAsString(pojo);
            return str;
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public static  <T> T stringToPojo (String jsonStr, TypeReference<T> type){
        try{
            T pojo = obj.readValue(jsonStr,type);
            return pojo;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T resourceToPojo (Resource resource, Class<T> type) throws IOException {

        String jsonStr = new String(Files.readAllBytes(resource.getFile()
                .toPath()));

        return  stringToPojo(jsonStr,type);
    }

    public static <T> T resourceToPojo (Resource resource, TypeReference<T> type) throws IOException {

        String jsonStr = new String(Files.readAllBytes(resource.getFile()
                .toPath()));

        return  stringToPojo(jsonStr,type);
    }



}
