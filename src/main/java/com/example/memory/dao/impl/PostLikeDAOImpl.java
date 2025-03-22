package com.example.memory.dao.impl;

import com.example.memory.constants.SQLQueryConstants;
import com.example.memory.dao.PostLikeDAO;
import org.checkerframework.common.util.count.report.qual.ReportCreation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PostLikeDAOImpl implements PostLikeDAO {
    private final JdbcTemplate jdbcTemplate;

    public PostLikeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public String upsertPostLike(String postId, String userId) {
        String likeId = UUID.randomUUID().toString();
        jdbcTemplate.update(SQLQueryConstants.INSERT_POST_LIKE, likeId, postId, userId);
        return likeId;
    }
    @Override
    public boolean deletePostLike(String postId, String userId) {
        return jdbcTemplate.update(SQLQueryConstants.DELETE_LIKE_FROM_POST, postId, userId) > 0;
    }
    @Override
    public Long fetchPostLikesCount(String postId) {
        return jdbcTemplate.queryForObject(SQLQueryConstants.FETCH_POST_LIKE_COUNT, Long.class, postId);
    }
    @Override
    public List<String> fetchPostLikes(String postId) {
        return jdbcTemplate.queryForList(SQLQueryConstants.FETCH_POST_LIKES, String.class, postId);
    }
    @Override
    public boolean isUserLikedPost(String postId, String userId) {
        Boolean result = jdbcTemplate.queryForObject(SQLQueryConstants.FETCH_IS_USER_LIKED_POST, Boolean.class, postId, userId);
        return result != null && result;
    }
}
