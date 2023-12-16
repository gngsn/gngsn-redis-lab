package com.gngsn.tweet.adapter.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "tweet_like")
data class TweetLikeEntity(
    @Id
    @Column(name = "tweet_id")
    val tweetId: Long,

    @Id
    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "created_at")
    val createdAt: LocalDateTime
)