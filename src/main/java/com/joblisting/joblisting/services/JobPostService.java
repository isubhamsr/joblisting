package com.joblisting.joblisting.services;

import com.joblisting.joblisting.Repository.JobPostRepository;
import com.joblisting.joblisting.models.JobPost;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JobPostService implements IJobPostService{

    @Autowired
    JobPostRepository repo;
    @Override
    public List<JobPost> getAllPost() {
        try{
            List<JobPost> job = repo.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
            if(job.isEmpty()){
                return job;
            }
            return job;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public JobPost savePost(JobPost post) {
        try{
            return repo.save(post);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
