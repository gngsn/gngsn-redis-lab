package com.gngsn.tweet.application.port.output

interface DeleteTweetOutputPort {
    fun delete(tweetId: Long)
}