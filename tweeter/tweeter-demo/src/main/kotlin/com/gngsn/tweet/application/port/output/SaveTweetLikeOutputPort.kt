package com.gngsn.tweet.application.port.output

interface SaveTweetLikeOutputPort {
    fun save(tweetId: Long, userId: Long)
}