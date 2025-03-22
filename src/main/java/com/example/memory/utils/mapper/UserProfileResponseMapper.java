package com.example.memory.utils.mapper;

import com.example.memory.proto.api.UserProfileResponseDTO;
import com.example.memory.proto.entity.UserEntity.UserEntity;
import org.apache.catalina.User;

public class UserProfileResponseMapper {
    public static UserProfileResponseDTO toUserProfileResponseDTO(UserEntity userEntity) {
        return UserProfileResponseDTO.newBuilder().setUserId(userEntity.getUserId())
                .setUsername(userEntity.getUsername())
                .setFirstName(userEntity.getFirstName())
                .setLastName(userEntity.getLastName())
                .setEmail(userEntity.getEmail())
                .setPhoneNumber(userEntity.getPhoneNumber())
                .setDateOfBirth(userEntity.getDateOfBirth())
                .setIsVerified(userEntity.getIsVerified())
                .setIsBlocked(userEntity.getIsBlocked())
                .setProfileImage(userEntity.getProfileImage())
                .setCoverImage(userEntity.getCoverImage())
                .build();
    }
}
