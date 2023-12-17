package com.gngsn.tweet.adapter.output.persistence.entity

import com.gngsn.tweet.application.domain.model.Tweet
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tweet")
data class TweetEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "message")
    val message: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime
) {
    fun toDomain(like: Long): Tweet = Tweet(
        id = this.id,
        userId = this.userId,
        message = this.message,
        like = like,
        createdAt = this.createdAt,
    )

    companion object {
        fun of(o: Tweet): TweetEntity = TweetEntity(
            id = null,
            userId = o.userId,
            message = o.message,
            createdAt = o.createdAt,
        )
    }
}