package com.project.jobportal.repository;

import com.project.jobportal.model.JobItem;

import java.util.List;

public interface SearchJobsRepository {
    public List<JobItem> findByText(String q);
}
