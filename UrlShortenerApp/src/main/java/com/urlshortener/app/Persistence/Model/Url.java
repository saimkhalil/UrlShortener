package com.urlshortener.app.Persistence.Model;

import lombok.Data;
import lombok.ToString;
import org.apache.coyote.Constants;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Data
@Document("Url")
public class Url
{
    private String longUrl;
    private String shortUrl;

    public static class Constants
    {
        public static final String LONG_URL = "longUrl";
        public static final String SHORT_URL = "shortUrl";
    }
}
