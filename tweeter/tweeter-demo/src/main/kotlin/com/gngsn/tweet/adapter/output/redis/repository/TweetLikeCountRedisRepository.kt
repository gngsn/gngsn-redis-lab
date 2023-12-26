package com.gngsn.tweet.adapter.output.redis.repository

import com.gngsn.tweet.adapter.output.redis.constant.TWEET_LIKE_KEY
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ZSetOperations.TypedTuple
import org.springframework.stereotype.Repository


@Repository
class TweetLikeCountRedisRepository(
    private val DB0RedisTemplate: RedisTemplate<String, Any>,
) {
    val zoperation = DB0RedisTemplate.opsForZSet()

    fun zadd(tweetId: Long, score: Double) {
        zoperation.incrementScore(TWEET_LIKE_KEY, tweetId, 1.0)
    }

    fun findAll(n: Long = -1): Set<ScoreValue> {
        val reverseRange = zoperation
            .rangeWithScores(TWEET_LIKE_KEY, 0, -1)
        return reverseRange?.map { ScoreValue.of(it) }?.toSet() ?: setOf()
    }

    fun remove(tweetId: Long) {
        zoperation.remove(TWEET_LIKE_KEY, tweetId)
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