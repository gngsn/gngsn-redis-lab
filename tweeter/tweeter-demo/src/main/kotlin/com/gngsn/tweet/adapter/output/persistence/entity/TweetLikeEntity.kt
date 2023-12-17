package com.gngsn.tweet.adapter.output.persistence.entity

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDateTime


@Entity
@Table(name = "tweet_like")
@IdClass(TweetLikeEntity.CompositeKey::class)
data class TweetLikeEntity(
    @Id
    @Column(name = "tweet_id")
    val tweetId: Long,

    @Id
    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()
) {

    @Embeddable
    data class CompositeKey(
        private val tweetId: Long,
        private val userId: Long,
    ) : Serializable
}