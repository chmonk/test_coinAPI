package com.example.coindesk.back.bean.response;

import com.example.coindesk.back.Enum.StatusEnum;
import com.example.coindesk.back.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.dao.DataRetrievalFailureException;

import java.io.Serializable;


@Getter
@Setter
public class CommonResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public T data;
    public String failReason = "";
    private String status = "";

    private String date = "";

    public CommonResponse(T data) {
        super();
        this.status = StatusEnum.SUCCESS.getDescription();
        this.data = data;
    }

    public CommonResponse(StatusEnum status, String failReason, T data) {
        super();
        this.status = status.getDescription();
        this.failReason = failReason;
        this.data = data;
    }

    public static CommonResponse setCurrentDate(CommonResponse res){
        res.date= DateUtil.getCurrentTimeString(DateUtil.yyyyMMddHHmmssSSS);
        return res;
    }


}
