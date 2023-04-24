package com.example.coindesk.back.exception;


import com.example.coindesk.back.Enum.StatusEnum;
import com.example.coindesk.back.bean.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import static com.example.coindesk.back.bean.response.CommonResponse.setCurrentDate;

@RestControllerAdvice
public class AllExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AllExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> handleAllNotCustomException(
            Exception exception
    ){
        logger.error("Unknown error occurred" + exception.getMessage());

        Writer buffer = getStackTrace(exception);
        logger.error(buffer.toString());

        return buildErrorResponse(
                exception,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CommonResponse> handleCustomException(
            CustomException exception
    ){
        logger.error("CustomException occurred. "+ exception.getMessage());

        return buildErrorResponse(
                exception,
                exception.getStatus()
        );
    }


    private ResponseEntity<CommonResponse> buildErrorResponse(
            Exception exception,
            HttpStatus httpStatus
    ) {

        CommonResponse errRes = new CommonResponse(
                StatusEnum.FAIL,exception.getMessage(),null
        );
        setCurrentDate(errRes);

        return ResponseEntity.status(httpStatus).body(errRes);
    }

    private Writer getStackTrace(Exception e){

        Writer buffer = new StringWriter();
        PrintWriter pw = new PrintWriter(buffer);
        e.printStackTrace(pw);

        return buffer;

    }


}
