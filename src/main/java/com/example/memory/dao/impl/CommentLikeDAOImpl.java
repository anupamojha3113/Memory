package com.example.memory.dao.impl;

import com.example.memory.constants.SQLQueryConstants;
import com.example.memory.dao.CommentLikeDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CommentLikeDAOImpl implements CommentLikeDAO {

    private final JdbcTemplate jdbcTemplate;

    public CommentLikeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public boolean addCommentLike(String likeId, String commentId, String userId) {
        int rowsAffected = jdbcTemplate.update(SQLQueryConstants.INSERT_COMMENT_LIKE, likeId, commentId, userId);
        return rowsAffected > 0;
    }
    @Override
    public Long fetchCommentLikesCount(String commentId) {
        return jdbcTemplate.queryForObject(SQLQueryConstants.FETCH_COMMENT_LIKE_COUNT, Long.class, commentId);
    }
    @Override
    public List<String> fetchCommentLikes(String commentId) {
        return jdbcTemplate.queryForList(SQLQueryConstants.FETCH_COMMENT_LIKES, String.class, commentId);
    }
    @Override
    public boolean isUserLikedComment(String commentId, String userId) {
        List<String> result = jdbcTemplate.queryForList(SQLQueryConstants.FETCH_IS_USER_LIKED_COMMENT, String.class, commentId, userId);
        return !result.isEmpty();
    }
    @Override
    public boolean deleteCommentLike(String commentId, String userId) {
        int rowsAffected = jdbcTemplate.update(SQLQueryConstants.DELETE_LIKE_FROM_COMMENT, commentId, userId);
        return rowsAffected > 0;
    }
}
