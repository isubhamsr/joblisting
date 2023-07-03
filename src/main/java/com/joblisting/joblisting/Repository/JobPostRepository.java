package com.joblisting.joblisting.Repository;

import com.joblisting.joblisting.models.JobPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobPostRepository extends MongoRepository<JobPost,String> {
}
