package com.gngsn.tweet.adapter.output.redis.repository

import com.gngsn.tweet.adapter.output.redis.ScoreValue
import com.gngsn.tweet.adapter.output.redis.constant.TWEET_UV_RANK_KEY
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class TweetUvRedisRepository(
    private val db01redisTemplate: RedisTemplate<String, Long>,
) {
    val ops = db01redisTemplate.opsForZSet()
    val KEY = TWEET_UV_RANK_KEY

    fun view(tweetId: Long) {
        ops.incrementScore(KEY, tweetId, 1.0)
    }

    fun top(n: Long = -1): SortedSet<ScoreValue> {
        val reverseRange = ops.reverseRangeWithScores(KEY, 0, n)
        return reverseRange?.map { ScoreValue(it.value!!, it.score!!) }?.toSortedSet() ?: sortedSetOf()
    }

    fun remove(tweetId: Long) {
        ops.remove(KEY, tweetId)
    }
}