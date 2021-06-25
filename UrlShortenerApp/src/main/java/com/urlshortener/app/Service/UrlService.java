package com.urlshortener.app.Service;

import com.urlshortener.app.Mapper.UrlMapper;
import com.urlshortener.app.Persistence.Model.Url;
import com.urlshortener.app.Persistence.Repository.UrlRepository;
import com.urlshortener.contracts.Requests.UrlRequest;
import com.urlshortener.contracts.Responses.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService
{
    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UrlMapper urlMapper;

    public ResponseModel<Url> createTinyUrl(UrlRequest urlRequest)
    {
        Optional<List<Url>> urlList = urlRepository.findUrl(urlRequest.getUrl());
        ResponseModel<Url> responseModel = new ResponseModel<>();
        responseModel.setMessage("Tiny URL created successfully");
        responseModel.setHttpStatus(HttpStatus.OK);
        if (urlList.isPresent() && !urlList.get().isEmpty())
        {
            responseModel.setData(urlList.get().get(0));
            return responseModel;
        }
        else
            {
            String uuid = UUID.randomUUID().toString().substring(0, 6);
//          UUID uuid = UUID.randomUUID();
            String tinyurl = "sho.rt/" + uuid;
            Url url = urlMapper.mapUrlRequestToUrl(tinyurl, urlRequest);
            url = urlRepository.save(url);
            responseModel.setData(url);
            return responseModel;
        }
    }

    public ResponseModel<Url> findTinyUrl(String shortUrl)
    {
        Optional<List<Url>> urlList = urlRepository.findShortUrl(shortUrl);
        ResponseModel<Url> responseModel = new ResponseModel<>();
        if (urlList.isPresent() && !urlList.get().isEmpty())
        {
            responseModel.setData(urlList.get().get(0));
            responseModel.setMessage("URL returned successfully");
            responseModel.setHttpStatus(HttpStatus.OK);
            return responseModel;
        }
        else
        {
            responseModel.setMessage("No such tiny url present in our Database");
            responseModel.setHttpStatus(HttpStatus.BAD_REQUEST);
            return responseModel;
        }
    }
}
