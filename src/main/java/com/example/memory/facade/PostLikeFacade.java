package com.example.memory.facade;

import com.example.memory.dao.PostLikeDAO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostLikeFacade {
    private final PostLikeDAO postLikeDAO;

    public PostLikeFacade(PostLikeDAO postLikeDAO) {
        this.postLikeDAO = postLikeDAO;
    }

    public String upsertPostLike(String postId, String userId){
        return postLikeDAO.upsertPostLike(postId, userId);
    }

    public boolean deletePostLike(String postId, String userId){
        return postLikeDAO.deletePostLike(postId, userId);
    }

    public Long fetchPostLikesCount(String postId){
        return postLikeDAO.fetchPostLikesCount(postId);
    }

    public List<String> fetchPostLikes(String postId){
        return postLikeDAO.fetchPostLikes(postId);
    }

    public boolean isUserLikedPost(String postId, String userId){
        return postLikeDAO.isUserLikedPost(postId, userId);
    }
}
