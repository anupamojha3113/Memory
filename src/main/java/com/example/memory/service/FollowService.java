package com.example.memory.service;

import com.example.memory.constants.enums.StatusCodes;
import com.example.memory.exception.GenericError;
import com.example.memory.facade.FollowFacade;
import com.example.memory.facade.UserFacade;
import com.example.memory.proto.ApiResponse.ApiResponse;
import com.example.memory.proto.entity.UserEntity.UserEntity;
import com.example.memory.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowService {
    Logger LOGGER = LoggerFactory.getLogger(FollowService.class);
    private final UserFacade userFacade;
    private final FollowFacade followFacade;
    public FollowService(UserFacade userFacade, FollowFacade followFacade) {
        this.userFacade = userFacade;
        this.followFacade = followFacade;
    }

    public ResponseEntity<ApiResponse> followUser(String followerProfileId, String followingProfileId) {
        Optional<UserEntity> followerEntity = userFacade.getUserDetailsByUserProfileId(followerProfileId);
        Optional<UserEntity> followingEntity = userFacade.getUserDetailsByUserProfileId(followingProfileId);

        if(followerEntity.isEmpty() || followingEntity.isEmpty()) {
            throw new GenericError(HttpStatus.NOT_FOUND, StatusCodes.ERROR_USER_NOT_FOUND);
        }
        followFacade.upsertFollower(followerProfileId,followingProfileId);
        return ResponseUtils.buildResponse(HttpStatus.OK, true, StatusCodes.SUCCESS_FOLLOWER_CREATED);
    }

}
