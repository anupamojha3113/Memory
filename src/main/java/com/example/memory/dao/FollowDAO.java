package com.example.memory.dao;

import java.util.List;
import com.example.memory.utils.PaginationUtils;

public interface FollowDAO {
    public void upsertFollower(String followerProfileId, String followingProfileId);
    public Long getCountOfFollowers(String userProfileId);
    public Long getCountOfFollowings(String userProfileId);
    public List<String> listFollowers(String userProfileId, PaginationUtils.Pagination pagination);
    public List<String> listFollowing(String userProfileId, PaginationUtils.Pagination pagination);
}
