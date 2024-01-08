package com.project.jobportal.repository;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.project.jobportal.model.JobItem;
import com.mongodb.client.AggregateIterable;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchImplementation implements SearchJobsRepository{

    @Autowired
    MongoClient mongoClient;

    @Autowired
    MongoConverter converter;

    @Override
    public List<JobItem> findByText(String q) {
        final List<JobItem> posts = new ArrayList<>();

        MongoDatabase database = mongoClient.getDatabase("job-portal");
        MongoCollection<Document> collection = database.getCollection("jobs");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                new Document("$search",
                new Document("index", "jobIndex").append("text",
                new Document("query", q).append("path", Arrays.asList("profile", "desc", "techs")))),
                new Document("$sort",
                new Document("exp", 1L))));

        result.forEach(doc->posts.add(converter.read(JobItem.class, doc)));

        return posts;
    }
}
