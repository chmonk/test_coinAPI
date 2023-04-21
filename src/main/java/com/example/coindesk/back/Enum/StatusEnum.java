package com.example.coindesk.back.Enum;

import lombok.Getter;

@Getter
public enum StatusEnum {
    FAIL(0,"fail"),
    SUCCESS(1,"success");
    private int num;
    private String description;

    StatusEnum(int num,String description){
        this.num=num;
        this.description=description;
    }


}
