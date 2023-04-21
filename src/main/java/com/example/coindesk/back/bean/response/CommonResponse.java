package com.example.coindesk.back.bean.response;

import com.example.coindesk.back.Enum.StatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> implements Serializable {
        private static final long serialVersionUID = 1L;

        public T data;
        public String failReason;

        private String status ;

    public CommonResponse(T data) {
        super();
        this.status = StatusEnum.SUCCESS.getDescription();
        this.data = data;
    }

        public CommonResponse(StatusEnum status,String failReason, T data) {
            super();
            this.status = status.getDescription();
            this.failReason = failReason;
            this.data = data;
        }




}
