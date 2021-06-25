package com.urlshortener.app.Validator;

import com.urlshortener.app.Utils.SError;
import com.urlshortener.contracts.Requests.UrlRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UrlValidator
{
    public void validateUrlRequest(UrlRequest userRequest) throws SError
    {
        if (null == userRequest.getUrl() || "".equals(userRequest.getUrl().trim()))
        {
            throw new SError("Url cannot be Empty", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateLongUrlRequest(String shortUrl) throws SError
    {
        if (null == shortUrl || "".equals(shortUrl.trim()))
        {
            throw new SError("Url cannot be Empty",HttpStatus.BAD_REQUEST);
        }
    }
}
