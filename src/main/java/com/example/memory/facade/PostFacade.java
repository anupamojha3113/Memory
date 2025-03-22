package com.example.memory.facade;

import com.example.memory.dao.PostDAO;
import com.example.memory.proto.entity.PostEntity.PostEntity;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class PostFacade {
    private final PostDAO postDAO;

    public PostFacade(PostDAO postDAO) {
        this.postDAO = postDAO;
    }
    public Optional<PostEntity> addPost(String userId, String postId, String textContent, List<String> mediaUrls){
        return postDAO.addPost(userId, postId, textContent, mediaUrls);
    }

    public boolean deletePost(String userId, String postId){
        return postDAO.deletePost(userId, postId);
    }

    public List<PostEntity> findAllPosts(String userId, int limit, int offset){
        return postDAO.findAllPosts(userId, limit, offset);
    }

    public Optional<PostEntity> findPostById(String postId){
        return postDAO.findPostById(postId);
    }
}
