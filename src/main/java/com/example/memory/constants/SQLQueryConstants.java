package com.example.memory.constants;

public class SQLQueryConstants {
    // language=MySQL
    public static final String CREATE_USER_PROFILE =
        """
        INSERT INTO UserProfile (
            userId, username, firstName, lastName, email, phoneNumber, password,
            profileImage, coverImage, description, dateOfBirth
        ) VALUES (UUID_TO_BIN(?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
        """;

    // language=MySQL
    public static final String FETCH_USER =
        "SELECT * FROM UserProfile WHERE userId = UUID_TO_BIN(?)";

    // language=MySQL
    public static final String FETCH_USER_PROFILE =
        "SELECT * FROM UserProfile WHERE userId = UUID_TO_BIN(?)";

    // language=MySQL
    public static final String FETCH_FOLLOWERS_FORWARD =
        """
        SELECT BIN_TO_UUID(followerId) FROM UserFollows 
        WHERE followingId = UUID_TO_BIN(?) 
        AND createdAt BETWEEN ? AND ? 
        ORDER BY createdAt ASC 
        LIMIT ?;
        """;

    // language=MySQL
    public static final String FETCH_FOLLOWERS_BACKWARD =
        """
        SELECT BIN_TO_UUID(followerId) FROM UserFollows 
        WHERE followingId = UUID_TO_BIN(?) 
        AND createdAt BETWEEN ? AND ? 
        ORDER BY createdAt DESC 
        LIMIT ?;
        """;

    // language=MySQL
    public static final String FETCH_COUNT_OF_FOLLOWERS =
        "SELECT COUNT(*) FROM UserFollows WHERE followingId = UUID_TO_BIN(?)";

    // language=MySQL
    public static final String FETCH_FOLLOWING_FORWARD =
        """
        SELECT BIN_TO_UUID(followingId) FROM UserFollows 
        WHERE followerId = UUID_TO_BIN(?) 
        AND createdAt BETWEEN ? AND ? 
        ORDER BY createdAt ASC 
        LIMIT ?;
        """;

    // language=MySQL
    public static final String FETCH_FOLLOWING_BACKWARD =
        """
        SELECT BIN_TO_UUID(followingId) FROM UserFollows 
        WHERE followerId = UUID_TO_BIN(?) 
        AND createdAt BETWEEN ? AND ? 
        ORDER BY createdAt DESC 
        LIMIT ?;
        """;

    // language=MySQL
    public static final String FETCH_COUNT_OF_FOLLOWING =
        "SELECT COUNT(*) FROM UserFollows WHERE followerId = UUID_TO_BIN(?)";

    // language=MySQL
    public static final String UPSERT_FOLLOWING =
        """
            INSERT IGNORE INTO UserFollows (followerId, followingId)
            VALUES (UUID_TO_BIN(?), UUID_TO_BIN(?));
        """;

    // language=MySQL
    public static final String INSERT_POST_LIKE =
        """
            INSERT IGNORE INTO PostLikes (likeId, postId, userId)
            VALUES (UUID_TO_BIN(?), UUID_TO_BIN(?), UUID_TO_BIN(?));
        """;

    // language=MySQL
    public static final String FETCH_POST_LIKE_COUNT =
        """
            SELECT COUNT(*) FROM PostLikes
            Where postId = UUID_TO_BIN(?);
        """;

    // language=MySQL
    public static final String FETCH_POST_LIKES =
        """
            SELECT userId FROM PostLikes
            Where postId = UUID_TO_BIN(?);
        """;

    // language=MySQL
    public static final String FETCH_IS_USER_LIKED_POST =
        """
            SELECT userId FROM PostLikes
            Where postId = UUID_TO_BIN(?) AND userId = UUID_TO_BIN(?);
        """;

    // language=MySQL
    public static final String DELETE_LIKE_FROM_POST =
        """
            DELETE FROM PostLikes
            WHERE postId = UUID_TO_BIN(?) AND  userId = UUID_TO_BIN(?);
        """;

    // language=MySQL
    public static final String INSERT_COMMENT_LIKE =
        """
            INSERT IGNORE INTO CommentLikes (likeId, commentId, userId)
            VALUES (UUID_TO_BIN(?), UUID_TO_BIN(?), UUID_TO_BIN(?));
        """;

    // language=MySQL
    public static final String FETCH_COMMENT_LIKE_COUNT =
        """
            SELECT COUNT(*) FROM CommentLikes
            WHERE commentId = UUID_TO_BIN(?);
        """;

    // language=MySQL
    public static final String FETCH_COMMENT_LIKES =
        """
            SELECT userId FROM CommentLikes
            WHERE commentId = UUID_TO_BIN(?);
        """;

    // language=MySQL
    public static final String FETCH_IS_USER_LIKED_COMMENT =
        """
            SELECT userId FROM CommentLikes
            WHERE commentId = UUID_TO_BIN(?) AND userId = UUID_TO_BIN(?);
        """;

    // language=MySQL
    public static final String DELETE_LIKE_FROM_COMMENT =
        """
            DELETE FROM CommentLikes
            WHERE commentId = UUID_TO_BIN(?) AND userId = UUID_TO_BIN(?);
        """;

    // language=MySQL
    public static final String ADD_POST =
        """
            INSERT INTO Posts (postId, userId, textContent, mediaUrls)
            VALUES (UUID_TO_BIN(?), UUID_TO_BIN(?), ?, ?);
        """;

    // language=MySQL
    public static final String DELETE_POST =
        """
            DELETE FROM Posts
            WHERE postId = UUID_TO_BIN(?) AND userId = UUID_TO_BIN(?);
        """;

    // language=MySQL
    public static final String FIND_ALL_POSTS_BY_USER =
        """
            SELECT BIN_TO_UUID(postId) AS postId, BIN_TO_UUID(userId) AS userId, textContent, mediaUrls, createdAt, updatedAt
            FROM Posts
            WHERE userId = UUID_TO_BIN(?)
            ORDER BY createdAt DESC
            LIMIT ? OFFSET ?;
        """;


    // language=MySQL
    public static final String FIND_POST_BY_ID =
        """
            SELECT BIN_TO_UUID(postId) AS postId, BIN_TO_UUID(userId) AS userId, textContent, mediaUrls, createdAt, updatedAt
            FROM Posts
            WHERE postId = UUID_TO_BIN(?);
        """;

}
