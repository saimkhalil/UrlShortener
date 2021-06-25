package com.urlshortener.app.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class SError extends Exception
{
    private String message;
    private HttpStatus httpStatus;

}
