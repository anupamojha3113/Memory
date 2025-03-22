CREATE TABLE IF NOT EXISTS UserProfile (
    userId           BINARY(16)     NOT NULL UNIQUE PRIMARY KEY,
    username        VARCHAR(255)    NOT NULL UNIQUE,
    firstName       VARCHAR(255)    NOT NULL,
    lastName        VARCHAR(255)    NOT NULL,
    email           VARCHAR(255)    NOT NULL UNIQUE,
    phoneNumber     VARCHAR(20)     NOT NULL UNIQUE,
    password        VARCHAR(255)    NOT NULL,
    profileImage    VARCHAR(255)    NULL,
    coverImage      VARCHAR(255)    NULL,
    description     TEXT            NULL,
    dateOfBirth     DATE            NULL,
    role            ENUM('USER', 'ADMIN', 'MODERATOR') NOT NULL DEFAULT 'USER',
    isVerified      BOOLEAN         DEFAULT FALSE NOT NULL,
    isBlocked       BOOLEAN         DEFAULT FALSE NOT NULL,
    createdAt       TIMESTAMP(6)    DEFAULT CURRENT_TIMESTAMP(6) NOT NULL,
    updatedAt       TIMESTAMP(6)    DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) NOT NULL,

    INDEX username_index (username),
    INDEX email_index (email)
    );

CREATE TABLE IF NOT EXISTS Posts (
    postId          BINARY(16)     NOT NULL UNIQUE PRIMARY KEY,
    userId         BINARY(16)     NOT NULL,
    textContent     TEXT           NULL,
    mediaUrls       JSON           NULL,
    createdAt       TIMESTAMP(6)   DEFAULT CURRENT_TIMESTAMP(6) NOT NULL,
    updatedAt       TIMESTAMP(6)   DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) NOT NULL,
    INDEX user_index (userId)
);

CREATE TABLE IF NOT EXISTS PostComments (
    commentId        BINARY(16)     NOT NULL UNIQUE PRIMARY KEY,
    postId           BINARY(16)     NOT NULL,
    userId           BINARY(16)     NOT NULL,
    content          TEXT           NOT NULL,
    likes            INT            DEFAULT 0,
    createdAt        TIMESTAMP(6)   DEFAULT CURRENT_TIMESTAMP(6) NOT NULL,
    updatedAt        TIMESTAMP(6)   DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) NOT NULL,

    INDEX post_index (postId)
    );

CREATE TABLE IF NOT EXISTS CommentLikes (
    likeId     BINARY(16)    NOT NULL UNIQUE PRIMARY KEY,
    commentId  BINARY(16)    NOT NULL,
    userId     BINARY(16)    NOT NULL,
    createdAt  TIMESTAMP(6)  DEFAULT CURRENT_TIMESTAMP(6) NOT NULL,

    UNIQUE KEY unique_like (commentId, userId)
    );

CREATE TABLE IF NOT EXISTS CommentReplies (
    replyId          BINARY(16)     NOT NULL UNIQUE PRIMARY KEY,
    postId           BINARY(16)     NOT NULL,
    parentCommentId  BINARY(16)     NOT NULL,
    userId           BINARY(16)     NOT NULL,
    content          TEXT           NOT NULL,
    createdAt        TIMESTAMP(6)   DEFAULT CURRENT_TIMESTAMP(6) NOT NULL,
    updatedAt        TIMESTAMP(6)   DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) NOT NULL,

    INDEX post_index (postId)
);

CREATE TABLE IF NOT EXISTS PostLikes (
    likeId     BINARY(16)    NOT NULL UNIQUE PRIMARY KEY,
    postId     BINARY(16)    NOT NULL,
    userId     BINARY(16)    NOT NULL,
    createdAt  TIMESTAMP(6)  DEFAULT CURRENT_TIMESTAMP(6) NOT NULL,

    UNIQUE KEY unique_like (postId, userId)
    );

CREATE TABLE IF NOT EXISTS UserFollows (
    followerId   BINARY(16)     NOT NULL, -- The user who follows
    followingId  BINARY(16)     NOT NULL, -- The user being followed
    createdAt    TIMESTAMP(6)   DEFAULT CURRENT_TIMESTAMP(6) NOT NULL,

    PRIMARY KEY (followerId, followingId)
    );
