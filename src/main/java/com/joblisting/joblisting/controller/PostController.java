package com.joblisting.joblisting.controller;

import com.joblisting.joblisting.Repository.JobPostRepository;
import com.joblisting.joblisting.models.JobPost;
import com.joblisting.joblisting.services.JobPostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    JobPostRepository repo;
    @Autowired
    JobPostService post;

    Dictionary<String, Object> response= new Hashtable<>();
    @GetMapping("/")
    public String home(){
        return "This is Home page";
    }

    @GetMapping("/posts")
    public Dictionary getAllPost() {
        var jobs = post.getAllPost();
        if(jobs.isEmpty()){
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("error", true);
            response.put("message", "Jobs not found");
            return response;
        }
        response.put("status", HttpStatus.OK.value());
        response.put("error", false);
        response.put("message", "Jobs found");
        response.put("data", post.getAllPost());
        return response;
    }
    @PostMapping("/post")
    public JobPost addPost(@RequestBody JobPost post){
       return repo.save(post);
    }
}
