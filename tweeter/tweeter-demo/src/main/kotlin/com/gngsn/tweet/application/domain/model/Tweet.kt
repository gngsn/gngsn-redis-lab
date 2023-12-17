package com.gngsn.tweet.application.domain.model

import java.time.LocalDateTime

class Tweet(
    val id: Long? = null,
    val userId: Long,
    val message: String,
    val like: Long = 0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {

}
