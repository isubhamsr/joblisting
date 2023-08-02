package com.joblisting.joblisting.services;

import com.joblisting.joblisting.models.JobPost;

import java.util.List;
import java.util.Optional;

public interface IJobPostService {
    List<JobPost> getAllPost();
    JobPost savePost(JobPost post);
    Optional<JobPost> getPost(String id);
    List<JobPost> updatePost(JobPost post);
    boolean deletePost(String id);
}
