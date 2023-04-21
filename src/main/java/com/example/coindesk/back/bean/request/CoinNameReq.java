package com.example.coindesk.back.bean.request;

import com.example.coindesk.back.bean.CoinName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
public class CoinNameReq {
    @JsonProperty(value="id")
    private Integer id;

    @JsonProperty(value="en")
    private String enName ;

    @JsonProperty(value="ch")
    private String chName;

    public CoinName toCoinName(){

        CoinNameReq a=this;

        CoinName temp=new CoinName();

        if(this.id!=null)temp.setId(this.id);
        temp.setEnName(this.enName);
        temp.setChName(this.chName);

        return temp;

    }

}
