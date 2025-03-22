package com.example.memory.facade;

import com.example.memory.dao.UserDAO;
import com.example.memory.proto.entity.UserEntity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserFacade {
    public static Logger logger = LoggerFactory.getLogger(UserFacade.class);
    private final UserDAO userDao;

    public UserFacade(UserDAO userDao) {
        this.userDao = userDao;
    }


    public Optional<UserEntity> getUserDetailsByUserProfileId(String userProfileId) {
        logger.info("Fetching user with userProfileId: {}", userProfileId);
        return userDao.getUserDetailsByUserProfileId(userProfileId);
    }

    public Optional<UserEntity> getUserDetailsByEmailOrPhoneNumber(String email, String phoneNumber) {
        logger.info("Fetching user with email : {} and phoneNumber : {}", email, phoneNumber);
        return userDao.getUserDetailsByEmailOrPhoneNumber(email, phoneNumber);
    }

//    public DeleteUserResponseDTO deleteUser(String username) {
//        logger.info("Deleting user with username: {}", username);
//        return userDao.deleteUser(username);
//    }

    public Optional<UserEntity>  createUser(String username, String firstName, String lastName, String email, String phoneNumber, String password,
                                            String profileImageURL, String coverImageURL, String description, String dateOfBirth) {
            logger.info("Creating user with username: {}", username);
            return userDao.createUser(username, firstName, lastName, email, phoneNumber, password,
                     profileImageURL, coverImageURL, description, dateOfBirth);
        }
}
