package com.example.memory.dao.impl;

import com.example.memory.dao.PostDAO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.memory.constants.SQLQueryConstants;
import com.example.memory.proto.entity.PostEntity.PostEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PostDAOImpl implements PostDAO {

    private final JdbcTemplate jdbcTemplate;

    public PostDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Optional<PostEntity> addPost(String userId, String postId, String textContent, List<String> mediaUrls) {
        String mediaUrlsJson = convertListToJson(mediaUrls);
        int rowsAffected = jdbcTemplate.update(SQLQueryConstants.ADD_POST, postId, userId, textContent, mediaUrlsJson);

        if (rowsAffected > 0) {
            return findPostById(postId);
        }
        return Optional.empty();
    }
    @Override
    public boolean deletePost(String userId, String postId) {
        int rowsAffected = jdbcTemplate.update(SQLQueryConstants.DELETE_POST, postId, userId);
        return rowsAffected > 0;
    }
    @Override
    public List<PostEntity> findAllPosts(String userId, int limit, int offset) {
        return jdbcTemplate.query(
            SQLQueryConstants.FIND_ALL_POSTS_BY_USER,
            this::mapRowToPostEntity,
            userId, limit, offset
        );
    }
    @Override
    public Optional<PostEntity> findPostById(String postId) {
        List<PostEntity> posts = jdbcTemplate.query(
            SQLQueryConstants.FIND_POST_BY_ID,
            this::mapRowToPostEntity,
            postId
        );
        return posts.isEmpty() ? Optional.empty() : Optional.of(posts.getFirst());
    }
    @Override
    public PostEntity mapRowToPostEntity(ResultSet rs, int rowNum) throws SQLException {
        return PostEntity.newBuilder()
            .setPostId(rs.getString("postId"))
            .setUserId(rs.getString("userId"))
            .setTextContent(rs.getString("textContent"))
            .setCreatedAt(rs.getTimestamp("createdAt").toInstant().toString())
            .addAllMediaUrls(convertJsonToList(rs.getString("mediaUrls")))
            .build();
    }
    @Override
    public String convertListToJson(List<String> mediaUrls) {
        return mediaUrls == null ? "[]" : new Gson().toJson(mediaUrls);
    }
    @Override
    public List<String> convertJsonToList(String json) {
        return new Gson().fromJson(json, new TypeToken<List<String>>(){}.getType());
    }
}

