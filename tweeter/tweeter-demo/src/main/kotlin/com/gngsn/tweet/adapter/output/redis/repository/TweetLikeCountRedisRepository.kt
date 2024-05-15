package com.gngsn.tweet.adapter.output.redis.repository

import com.gngsn.tweet.adapter.output.redis.ScoreValue
import com.gngsn.tweet.adapter.output.redis.constant.TWEET_LIKE_KEY
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository


@Repository
class TweetLikeCountRedisRepository(
    private val db0redisTemplate: RedisTemplate<String, Any>,
) {
    val ops = db0redisTemplate.opsForZSet()
    val KEY = TWEET_LIKE_KEY

    fun zadd(tweetId: Long, score: Double) {
        ops.incrementScore(KEY, tweetId, 1.0)
    }

    fun findAll(n: Long = -1): Set<ScoreValue> {
        val reverseRange = ops.rangeWithScores(KEY, 0, -1)
        return reverseRange?.map { ScoreValue.of(it) }?.toSet() ?: setOf()
    }

    fun remove(tweetId: Long) {
        ops.remove(KEY, tweetId)
    }
}