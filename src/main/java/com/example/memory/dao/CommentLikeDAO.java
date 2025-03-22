package com.example.memory.dao;

import java.util.List;

public interface CommentLikeDAO {
    boolean addCommentLike(String likeId, String commentId, String userId);

    Long fetchCommentLikesCount(String commentId);

    List<String> fetchCommentLikes(String commentId);

    boolean isUserLikedComment(String commentId, String userId);

    boolean deleteCommentLike(String commentId, String userId);
}
