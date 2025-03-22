package com.example.memory.controller;

import com.example.memory.proto.ApiResponse.ApiResponse;
import com.example.memory.service.FollowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class FollowController {
    Logger LOGGER = LoggerFactory.getLogger(FollowController.class);
    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/followRequest/{follower}/{following}")
    ResponseEntity<ApiResponse> followUser(@PathVariable("follower") String follower, @PathVariable("following") String following){
        return followService.followUser(follower,following);
    }
}
