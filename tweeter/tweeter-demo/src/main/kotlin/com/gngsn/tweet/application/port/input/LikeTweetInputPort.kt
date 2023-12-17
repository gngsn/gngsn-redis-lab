package com.gngsn.tweet.application.port.input

interface LikeTweetInputPort {
    fun like(tweetId: Long, userId: Long)
}