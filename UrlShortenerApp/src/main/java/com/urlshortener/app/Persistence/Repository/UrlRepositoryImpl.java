package com.urlshortener.app.Persistence.Repository;

import com.urlshortener.app.Persistence.Model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UrlRepositoryImpl implements UrlRepository
{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UrlRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public Url save(Url url)
    {
        return mongoTemplate.save(url);
    }

    @Override
    public Optional<List<Url>> findUrl(String url)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where(Url.Constants.LONG_URL).is(url));
        return Optional.of(mongoTemplate.find(query, Url.class));
    }

    @Override
    public Optional<List<Url>> findShortUrl(String url)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where(Url.Constants.SHORT_URL).is(url));
        return Optional.of(mongoTemplate.find(query, Url.class));
    }
}
