package com.gngsn.tweet.adapter.output.redis.entity

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("TweetLikeCount")
data class TweetLikeCountRedisEntity(

    @Indexed
    val tweetId: Long,
    val count: Long,
) {
//    @get:Id
//    var id: String? = null
//    @Indexed
//    @get:Reference
//    var actors: List<Actor> = listOf()
}