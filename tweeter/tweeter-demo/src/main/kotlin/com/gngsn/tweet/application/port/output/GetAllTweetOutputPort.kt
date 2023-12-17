package com.gngsn.tweet.application.port.output

import com.gngsn.tweet.application.domain.model.Tweet

interface GetAllTweetOutputPort {
    fun get(): List<Tweet>
}