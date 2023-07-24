package com.joblisting.joblisting.services;

import com.joblisting.joblisting.Repository.JobPostRepository;
import com.joblisting.joblisting.models.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostService implements IJobPostService{

    @Autowired
    JobPostRepository repo;
    @Override
    public List<JobPost> getAllPost() {
        List<JobPost> jobs = repo.findAll();
        if(jobs.isEmpty()){
            return jobs;
        }
        return jobs;
    }
}
