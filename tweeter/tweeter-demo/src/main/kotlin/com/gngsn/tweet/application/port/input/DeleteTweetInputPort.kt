package com.gngsn.tweet.application.port.input

interface DeleteTweetInputPort {
    fun delete(tweetId: Long)
}