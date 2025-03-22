package com.example.memory.facade;

import com.example.memory.dao.FollowDAO;
import com.example.memory.utils.CacheService;
import com.example.memory.utils.PaginationUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FollowFacade {
    private final FollowDAO followDAO;
    private final CacheService cacheService;

    private static final String FOLLOWER_COUNT_CACHE_KEY = "follower_count:";
    private static final String FOLLOWING_COUNT_CACHE_KEY = "following_count:";
    private static final String FOLLOWERS_LIST_CACHE_KEY = "followers_list:";
    private static final String FOLLOWING_LIST_CACHE_KEY = "following_list:";

    public FollowFacade(FollowDAO followDAO, CacheService cacheService) {
        this.followDAO = followDAO;
        this.cacheService = cacheService;
    }

    public void upsertFollower(String followerProfileId, String followingProfileId) {
        followDAO.upsertFollower(followerProfileId, followingProfileId);
        cacheService.evict(FOLLOWER_COUNT_CACHE_KEY + followingProfileId);
        cacheService.evict(FOLLOWING_COUNT_CACHE_KEY + followerProfileId);
        cacheService.evict(FOLLOWERS_LIST_CACHE_KEY + followerProfileId);
        cacheService.evict(FOLLOWING_LIST_CACHE_KEY + followerProfileId);
    }

    public Long getCountOfFollowers(String profileId) {
        String cacheKey = FOLLOWER_COUNT_CACHE_KEY + profileId;
        Long count = (Long) cacheService.get(cacheKey);
        if (count == null) {
            count = followDAO.getCountOfFollowers(profileId);
            cacheService.put(cacheKey, count);
        }
        return count;
    }

    public Long getCountOfFollowings(String profileId) {
        String cacheKey = FOLLOWING_COUNT_CACHE_KEY + profileId;
        Long count = (Long) cacheService.get(cacheKey);
        if (count == null) {
            count = followDAO.getCountOfFollowings(profileId);
            cacheService.put(cacheKey, count);
        }
        return count;
    }

    public List<String> listFollowers(String profileId, PaginationUtils.Pagination pagination) {
        String cacheKey = FOLLOWERS_LIST_CACHE_KEY + profileId + ":" + pagination.toString();
        List<String> followers = (List<String>) cacheService.get(cacheKey);
        if (followers == null) {
            followers = followDAO.listFollowers(profileId, pagination);
            cacheService.put(cacheKey, followers);
        }
        return followers;
    }

    public List<String> listFollowing(String profileId, PaginationUtils.Pagination pagination) {
        String cacheKey = FOLLOWING_LIST_CACHE_KEY + profileId + ":" + pagination.toString();
        List<String> following = (List<String>) cacheService.get(cacheKey);
        if (following == null) {
            following = followDAO.listFollowing(profileId, pagination);
            cacheService.put(cacheKey, following);
        }
        return following;
    }
}
