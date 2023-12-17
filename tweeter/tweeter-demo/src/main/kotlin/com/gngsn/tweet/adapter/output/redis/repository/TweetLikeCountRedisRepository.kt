package com.gngsn.tweet.adapter.output.redis.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ZSetOperations.TypedTuple
import org.springframework.stereotype.Repository

const val TWEET_LIKE_KEY = "tweet:likes"

@Repository
class TweetLikeCountRedisRepository(
    private val redisTemplate: RedisTemplate<String, Any>,
) {
    fun zadd(tweetId: Long, score: Double) {
        redisTemplate.opsForZSet().incrementScore(TWEET_LIKE_KEY, tweetId, 1.0)
    }

    fun findAll(n: Long = -1): Set<ScoreValue> {
        val reverseRange = redisTemplate.opsForZSet()
            .rangeWithScores(TWEET_LIKE_KEY, 0, -1)
        return reverseRange?.map { ScoreValue.of(it) }?.toSet() ?: setOf()
    }

    data class ScoreValue(
        val score: Double,
        val value: Long,
    ) {
        companion object {
            fun of(o: TypedTuple<Any>): ScoreValue =
                ScoreValue(
                    o.score ?: throw IllegalArgumentException(),
                    (o.value as Int).toLong(),
                )

        }
    }
}