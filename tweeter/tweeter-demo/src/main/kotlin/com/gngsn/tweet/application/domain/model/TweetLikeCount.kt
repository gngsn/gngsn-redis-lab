package com.gngsn.tweet.application.domain.model


class TweetLikeCount(
    val tweetId: Long,
    val userId: Long,
    val count: Long,
)