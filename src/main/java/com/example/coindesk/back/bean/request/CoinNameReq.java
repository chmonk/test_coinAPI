package com.example.coindesk.back.bean.request;

import com.example.coindesk.back.bean.CoinName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
@NoArgsConstructor
public class CoinNameReq {
    @JsonProperty(value="id")
    private Integer id;

    @JsonProperty(value="en")
    private String enName ;

    @JsonProperty(value="ch")
    private String chName;


    public CoinNameReq(String en,String ch){
        this.enName=en;
        this.chName=ch;
    }

    public CoinName toCoinName(){

        CoinName temp=new CoinName();

        if(this.id!=null)temp.setId(this.id);
        temp.setEnName(this.enName.toUpperCase());
        temp.setChName(this.chName);

        return temp;

    }

}
