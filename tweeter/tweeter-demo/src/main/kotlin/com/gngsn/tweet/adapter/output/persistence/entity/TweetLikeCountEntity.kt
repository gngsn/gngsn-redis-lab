package com.gngsn.tweet.adapter.output.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "tweet_like_count")
data class TweetLikeCountEntity(

    @Id
    @Column(name = "tweet_id")
    val tweetId: Long,

    @Column(name = "count")
    val count: Long,
)
