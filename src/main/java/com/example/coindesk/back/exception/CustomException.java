package com.example.coindesk.back.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends Exception{
    private String message;
    private HttpStatus status;
}
