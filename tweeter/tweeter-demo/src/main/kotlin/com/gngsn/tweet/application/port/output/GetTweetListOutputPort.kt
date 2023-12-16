package com.gngsn.tweet.application.port.output

import com.gngsn.tweet.application.domain.model.Tweet

interface GetTweetListOutputPort {
    fun get(): List<Tweet>
}