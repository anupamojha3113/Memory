package com.example.memory.dao.impl;

import com.example.memory.constants.SQLQueryConstants;
import com.example.memory.dao.FollowDAO;
import com.example.memory.utils.PaginationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class FollowDAOImpl implements FollowDAO {
    private final JdbcTemplate jdbcTemplate;

    public FollowDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void upsertFollower(String followerProfileId, String followingProfileId) {
        jdbcTemplate.update(SQLQueryConstants.UPSERT_FOLLOWING, followerProfileId, followingProfileId);
    }

    @Override
    public Long getCountOfFollowers(String userProfileId) {
        return jdbcTemplate.queryForObject(SQLQueryConstants.FETCH_COUNT_OF_FOLLOWERS, Long.class, userProfileId);
    }

    @Override
    public Long getCountOfFollowings(String userProfileId) {
        return jdbcTemplate.queryForObject(SQLQueryConstants.FETCH_COUNT_OF_FOLLOWING, Long.class, userProfileId);
    }

    @Override
    public List<String> listFollowers(String userProfileId, PaginationUtils.Pagination pagination) {
        Timestamp fromTs = Timestamp.valueOf("1970-01-01 00:00:00");
        Timestamp toTs = new Timestamp(System.currentTimeMillis());
        if (pagination.isForward()) {
            if (pagination.fromTs() != null) {
                fromTs = pagination.fromTs();
            }
        } else {
            if (pagination.toTs() != null) {
                toTs = pagination.toTs();
            }
        }
        String sql = (pagination.isForward()?SQLQueryConstants.FETCH_FOLLOWERS_FORWARD:SQLQueryConstants.FETCH_FOLLOWERS_BACKWARD);
        return jdbcTemplate.queryForList(sql, String.class, userProfileId, fromTs, toTs, pagination.limit());
    }

    @Override
    public List<String> listFollowing(String userProfileId, PaginationUtils.Pagination pagination) {
        Timestamp fromTs = Timestamp.valueOf("1970-01-01 00:00:00"); // Earliest timestamp
        Timestamp toTs = new Timestamp(System.currentTimeMillis()); // Current timestamp
        if (pagination.isForward()) {
            if (pagination.fromTs() != null) {
                fromTs = pagination.fromTs();
            }
        } else {
            if (pagination.toTs() != null) {
                toTs = pagination.toTs();
            }
        }

        String sql = (pagination.isForward()?SQLQueryConstants.FETCH_FOLLOWING_FORWARD:SQLQueryConstants.FETCH_FOLLOWING_BACKWARD);
        return jdbcTemplate.queryForList(sql, String.class, userProfileId, fromTs, toTs, pagination.limit());
    }
}
