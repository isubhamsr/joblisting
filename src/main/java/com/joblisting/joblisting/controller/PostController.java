package com.joblisting.joblisting.controller;

import com.joblisting.joblisting.Repository.JobPostRepository;
import com.joblisting.joblisting.models.JobPost;
import com.joblisting.joblisting.services.JobPostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    JobPostRepository repo;
    @Autowired
    JobPostService jobPost;


    Dictionary<String, Object> response= new Hashtable<>();
    @GetMapping("/")
    public String home(){
        return "This is Home page";
    }

    @GetMapping("/posts")
    @CrossOrigin(origins = "http://localhost:8000/", maxAge = 3600)
    public Dictionary getAllPost() {
        try{
            var jobs = jobPost.getAllPost();
            if(jobs.isEmpty()){
                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("error", true);
                response.put("message", "Jobs not found");
                return response;
            }
            response.put("status", HttpStatus.OK.value());
            response.put("error", false);
            response.put("message", "Jobs found");
            response.put("data", jobs);
            return response;
        }catch (Exception e){
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", true);
            response.put("message", e.getMessage());
            return response;
        }

    }
    @PostMapping("/post")
    @CrossOrigin
    public Dictionary addPost(@RequestBody JobPost post){
        try{
            if(post.getProfile() != "" &&
                    post.getExp() >= 0  &&
                        post.getTechs().length != 0 && post.getDesc() != ""){
                var savePost = jobPost.savePost(post);
                response.put("status", HttpStatus.CREATED.value());
                response.put("error", false);
                response.put("message", "Job posted");
                response.put("data", savePost);

                return response;
            }
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("error", true);
            response.put("message", "All fields are mandatory");
            return response;
        }catch (Exception e){
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", true);
            response.put("message", e.getMessage());
            return response;
        }
    }

    @PutMapping("/post")
    @CrossOrigin
    public Dictionary updatePost(@RequestBody JobPost post){
        try{
            if(post.getProfile() != "" &&
                    post.getExp() >= 0  &&
                    post.getTechs().length != 0 && post.getDesc() != ""){
                var updatePost = jobPost.updatePost(post);

                if (updatePost.isEmpty()){
                    response.put("status", HttpStatus.NOT_FOUND.value());
                    response.put("error", true);
                    response.put("message", "Job not posted");

                    return response;
                }
                response.put("status", HttpStatus.OK.value());
                response.put("error", false);
                response.put("message", "Job Updated");
                response.put("data", updatePost);

                return response;
            }
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("error", true);
            response.put("message", "All fields are mandatory");
            return response;
        }catch (Exception e){
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", true);
            response.put("message", e.getMessage());
            return response;
        }
    }

    @DeleteMapping("/post/{id}")
    @CrossOrigin
    public Dictionary deletePost(@PathVariable String id){
        try{
            boolean deletedPost = jobPost.deletePost(id);
            if (deletedPost){
                response.put("status", HttpStatus.OK.value());
                response.put("error", false);
                response.put("message", "Job Deleted");

                return response;
            }
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("error", true);
            response.put("message", "Job not Deleted");
            return response;
        }catch (Exception e){
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", true);
            response.put("message", e.getMessage());
            return response;
        }
    }

    @GetMapping("/post/{id}")
    @CrossOrigin
    public Dictionary getPost(@PathVariable String id){
        try {
            var post = jobPost.getPost(id);

            if(post.isEmpty()){
                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("error", true);
                response.put("message", "Job not Found");

                return response;
            }

            response.put("status", HttpStatus.OK.value());
            response.put("error", false);
            response.put("message", "Job Fetched");
            response.put("data", post);

            return response;
        }catch (Exception e){
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", true);
            response.put("message", e.getMessage());
            return response;
        }
    }
}
