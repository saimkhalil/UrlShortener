package com.urlshortener.contracts.Responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ResponseModel<T>
{
    private String message;
    private T data;
    private HttpStatus httpStatus;


}
