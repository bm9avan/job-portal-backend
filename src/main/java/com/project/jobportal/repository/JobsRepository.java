package com.project.jobportal.repository;

import com.project.jobportal.model.JobItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobsRepository extends MongoRepository<JobItem, String> {

}
