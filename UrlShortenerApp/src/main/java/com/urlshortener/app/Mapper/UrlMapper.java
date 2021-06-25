package com.urlshortener.app.Mapper;

import com.urlshortener.app.Persistence.Model.Url;
import com.urlshortener.contracts.Requests.UrlRequest;
import org.springframework.stereotype.Component;

@Component
public class UrlMapper
{
    public Url mapUrlRequestToUrl(String tinyUrl, UrlRequest urlRequest)
    {
        Url url = new Url();
        url.setLongUrl(urlRequest.getUrl());
        url.setShortUrl(tinyUrl);
        return url;
    }
}
