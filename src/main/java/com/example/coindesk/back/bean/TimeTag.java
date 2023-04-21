package com.example.coindesk.back.bean;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeTag {
    @JsonProperty("updated")
    public String updated;
    @JsonProperty("updatedISO")
    public String updatedISO;
    @JsonProperty("updateduk")
    public String updateduk;

}
