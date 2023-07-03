package com.joblisting.joblisting.controller;

import com.joblisting.joblisting.Repository.JobPostRepository;
import com.joblisting.joblisting.models.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    JobPostRepository repo;
    @GetMapping("/")
    public String home(){
        return "This is Home page";
    }

    @GetMapping("/posts")
    public List<JobPost> getAllPost(){
        return repo.findAll();
    }
    @PostMapping("/post")
    public JobPost addPost(@RequestBody JobPost post){
       return repo.save(post);
    }
}
