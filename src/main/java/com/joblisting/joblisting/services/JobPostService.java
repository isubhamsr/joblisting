package com.joblisting.joblisting.services;

import com.joblisting.joblisting.Repository.JobPostRepository;
import com.joblisting.joblisting.models.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    @Override
    public Optional<JobPost> getPost(String id) {
        try{
            var post = repo.findById(id);
            if(post.isEmpty()){
                return post;
            }
            return post;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<JobPost> updatePost(JobPost post) {
        try{
            List<JobPost> jobs = new ArrayList<>();
            var jobPost = repo.findById(post.getId());

            if(jobPost.isEmpty()){
                return jobs;
            }

            JobPost _jobPost = jobPost.get();
            _jobPost.setProfile(post.getProfile());
            _jobPost.setDesc(post.getDesc());
            _jobPost.setExp(post.getExp());
            _jobPost.setTechs(post.getTechs());

            jobs.add(repo.save(_jobPost));
            return jobs;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean deletePost(String id) {
        try{
            var post = repo.findById(id);
            if(post.isEmpty()){
                return false;
            }

            repo.deleteById(id);
            return true;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
