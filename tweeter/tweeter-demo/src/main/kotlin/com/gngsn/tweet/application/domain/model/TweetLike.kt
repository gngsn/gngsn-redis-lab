package com.gngsn.tweet.application.domain.model

import java.time.LocalDateTime


class TweetLike(
    val tweetId: Long,
    val userId: Long,
    val createdAt: LocalDateTime
)
