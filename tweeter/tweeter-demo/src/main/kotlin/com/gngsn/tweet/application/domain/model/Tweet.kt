package com.gngsn.tweet.application.domain.model

import java.time.LocalDateTime

class Tweet(
    val id: Long,
    val userId: Long,
    val message: String,
    val createdAt: LocalDateTime
)
