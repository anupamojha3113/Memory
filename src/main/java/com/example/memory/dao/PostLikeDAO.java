package com.example.memory.dao;

import java.util.List;

public interface PostLikeDAO {
    String upsertPostLike(String postId, String userId);

    boolean deletePostLike(String postId, String userId);

    Long fetchPostLikesCount(String postId);

    List<String> fetchPostLikes(String postId);

    boolean isUserLikedPost(String postId, String userId);
}
