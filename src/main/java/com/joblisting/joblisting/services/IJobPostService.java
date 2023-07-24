package com.joblisting.joblisting.services;

import com.joblisting.joblisting.models.JobPost;
import org.bson.Document;

import java.util.List;

public interface IJobPostService {
    List<JobPost> getAllPost();
    JobPost savePost(JobPost post);
}
