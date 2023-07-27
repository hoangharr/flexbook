package com.example.flexbook.controller;

import com.example.flexbook.models.Post;
import com.example.flexbook.models.User;
import com.example.flexbook.repository.PostRepo;
import com.example.flexbook.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ProjectController {
    @Autowired
    private PostRepo postRepo;

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/add_user")
    public User createUser(@RequestBody User user) {
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setProfile_picture(user.getProfile_picture());
        user.setBio(user.getBio());
        user.setCreated_at(new Date());
        user.setUpdated_at(new Date());
        return userRepo.save(user);
    }
}
