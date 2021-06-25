package com.urlshortener.app.Persistence.Repository;

import com.urlshortener.app.Persistence.Model.Url;

import java.util.List;
import java.util.Optional;

public interface UrlRepository
{
    Url save(Url url);

   Optional<List<Url>> findUrl(String url);

    Optional<List<Url>> findShortUrl(String url);
}
