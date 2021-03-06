package com.urlshortener.app.Controller;

import com.urlshortener.app.Persistence.Model.Url;
import com.urlshortener.app.Security.UserAuth;
import com.urlshortener.app.Service.UrlService;
import com.urlshortener.app.Utils.SError;
import com.urlshortener.app.Validator.UrlValidator;
import com.urlshortener.contracts.Requests.UrlRequest;
import com.urlshortener.contracts.Responses.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/urlShortener")
public class UrlShortenerController
{
    @Autowired
    private UrlService urlService;

    @Autowired
    private UrlValidator requestValidator;

    @Autowired
    private UserAuth userAuth;

    @RequestMapping(value = "/createShortUrl" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Url> create(@RequestBody UrlRequest urlRequest, @RequestParam("callingUserId") String callingUserId)
    {
        System.out.println("request : " + urlRequest);
        System.out.println("User id : " + callingUserId);
        ResponseModel<Url> responseModel = new ResponseModel<>();

        try
        {
            userAuth.authenticateUser(callingUserId);
            requestValidator.validateUrlRequest(urlRequest);
            responseModel = urlService.createTinyUrl(urlRequest);
        }
        catch (IOException e)
        {
         //swallow
        }
        catch (SError e)
        {
            responseModel.setMessage("Please enter a valid URL" + e.getMessage());
            responseModel.setHttpStatus(e.getHttpStatus());
        }
        return responseModel;
    }

    @RequestMapping(value = "/getLongUrl" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<Url> getLongUrl(@RequestParam("shortUrl") String shortUrl, @RequestParam("callingUserId") String callingUserId)
    {
        System.out.println("short url : " + shortUrl);
        System.out.println("User id : " + callingUserId);
        ResponseModel<Url> responseModel = new ResponseModel<>();
        try
        {
            userAuth.authenticateUser(callingUserId);
            requestValidator.validateLongUrlRequest(shortUrl);
            responseModel = urlService.findTinyUrl(shortUrl);
        }
        catch (IOException e)
        {
            responseModel.setMessage("Error please try again");
            responseModel.setHttpStatus(HttpStatus.BAD_GATEWAY);
        }
        catch (SError e)
        {
            responseModel.setMessage("Please enter a valid URL" + e.getMessage());
            responseModel.setHttpStatus(e.getHttpStatus());
        }
        return responseModel;

    }
}
