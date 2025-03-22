package com.example.memory.dao;

import com.example.memory.proto.entity.PostEntity.PostEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PostDAO {
    Optional<PostEntity> addPost(String userId, String postId, String textContent, List<String> mediaUrls);

    boolean deletePost(String userId, String postId);

    List<PostEntity> findAllPosts(String userId, int limit, int offset);

    Optional<PostEntity> findPostById(String postId);

    PostEntity mapRowToPostEntity(ResultSet rs, int rowNum) throws SQLException;

    String convertListToJson(List<String> mediaUrls);

    List<String> convertJsonToList(String json);
}
