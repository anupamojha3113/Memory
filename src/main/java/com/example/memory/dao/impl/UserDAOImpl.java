package com.example.memory.dao.impl;

import com.example.memory.dao.UserDAO;
import com.example.memory.dao.UserDAO;
import com.example.memory.proto.entity.UserEntity.UserEntity;
import com.example.memory.proto.enums.Roles;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.memory.constants.SQLQueryConstants.CREATE_USER_PROFILE;
import static com.example.memory.constants.SQLQueryConstants.FETCH_USER;

@Repository
public class UserDAOImpl implements UserDAO {
    private  final JdbcTemplate jdbcTemplate;

    public UserDAOImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<UserEntity> userRowMapper = (rs, rowNum) -> UserEntity.newBuilder()
            .setUserId(UUID.nameUUIDFromBytes(rs.getBytes("userId")).toString())
            .setUsername(rs.getString("username"))
            .setFirstName(rs.getString("firstName"))
            .setLastName(rs.getString("lastName"))
            .setEmail(rs.getString("email"))
            .setPhoneNumber(rs.getString("phoneNumber"))
            .setPassword(rs.getString("password"))
            .setProfileImage(rs.getString("profileImage"))
            .setCoverImage(rs.getString("coverImage"))
            .setDescription(rs.getString("description"))
            .setDateOfBirth(rs.getString("dateOfBirth"))
            .setRole(Roles.valueOf(rs.getString("role")))
            .setIsVerified(rs.getBoolean("isVerified"))
            .setIsBlocked(rs.getBoolean("isBlocked"))
            .setCreatedAt(rs.getString("createdAt"))
            .setUpdatedAt(rs.getString("updatedAt"))
            .build();


    @Override
    public Optional<UserEntity> getUserDetailsByUserProfileId(String userId) {
        List<UserEntity> user = jdbcTemplate.query(FETCH_USER, userRowMapper, userId);
        return user.isEmpty() ? Optional.empty() : Optional.of(user.get(0));
    }

    @Override
    public Optional<UserEntity> getUserDetailsByEmailOrPhoneNumber(String email, String phoneNumber) {
        String query = "SELECT * FROM UserProfile WHERE email = ? OR phoneNumber = ?";
        List<UserEntity> user = jdbcTemplate.query(FETCH_USER, userRowMapper, email, phoneNumber);
        if (user.isEmpty()) return Optional.empty();
        else return Optional.of(user.getFirst());
    }


    @Override
    public Optional<UserEntity> createUser(String username, String firstName, String lastName, String email, String phoneNumber, String password,
                                           String profileImageURL, String coverImageURL, String description, String dateOfBirth) {
        String userId = UUID.randomUUID().toString();
        int cnt = jdbcTemplate.update(
                CREATE_USER_PROFILE,
                userId,
                username,
                firstName,
                lastName,
                email,
                phoneNumber,
                password,
                profileImageURL,
                coverImageURL,
                description,
                dateOfBirth
        );
        return getUserDetailsByUserProfileId(userId);
    }
}
