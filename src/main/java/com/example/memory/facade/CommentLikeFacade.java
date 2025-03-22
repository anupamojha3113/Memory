package com.example.memory.facade;

import com.example.memory.dao.CommentLikeDAO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentLikeFacade {
    private final CommentLikeDAO commentLikeDAO;

    public CommentLikeFacade(CommentLikeDAO commentLikeDAO) {
        this.commentLikeDAO = commentLikeDAO;
    }

    boolean addCommentLike(String likeId, String commentId, String userId){
        return commentLikeDAO.addCommentLike(likeId, commentId, userId);
    }

    Long fetchCommentLikesCount(String commentId){
        return commentLikeDAO.fetchCommentLikesCount(commentId);
    }

    List<String> fetchCommentLikes(String commentId){
        return commentLikeDAO.fetchCommentLikes(commentId);
    }

    boolean isUserLikedComment(String commentId, String userId){
        return commentLikeDAO.isUserLikedComment(commentId, userId);
    }

    boolean deleteCommentLike(String commentId, String userId){
        return commentLikeDAO.deleteCommentLike(commentId, userId);
    }
}
