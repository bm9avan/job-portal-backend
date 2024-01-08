package com.project.jobportal.controller;

import com.project.jobportal.model.JobItem;
import com.project.jobportal.repository.JobsRepository;
import com.project.jobportal.repository.SearchJobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://job-portal-bm9avan.vercel.app/")
public class JobItemsController {
    @Autowired
    JobsRepository repo;
    @Autowired
    SearchJobsRepository srepo;
    @GetMapping("/get")
    @CrossOrigin
    public List<JobItem> getData(){
        return repo.findAll();
    }

    @PostMapping("/post")
    @CrossOrigin
    public JobItem addData(@RequestBody JobItem item){return repo.save(item);}

    @GetMapping("/get/{q}")
    @CrossOrigin
    public List<JobItem> getData(@PathVariable String q){
        return srepo.findByText(q);
    }
}