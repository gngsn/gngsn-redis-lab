package com.gngsn.tweet.adapter.persistence.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tweet")
data class TweetEntity (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "message")
    val message: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime
)