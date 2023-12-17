package com.gngsn.tweet.adapter.output.redis.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class TweetLikeCountRedisRepository(
    private val redisTemplate: RedisTemplate<String, Any>,
) {

    val KEY = "tweet:likes"

    fun zadd(tweetId: Long, score: Double) {
        redisTemplate.opsForZSet().incrementScore(KEY, tweetId, 1.0)
    }

    fun findTopN(n: Long = 1000): Set<Long> {
        val reverseRange = redisTemplate.opsForZSet()
            .reverseRange(KEY, 0, n)
        return reverseRange as Set<Long>? ?: setOf()
    }
}