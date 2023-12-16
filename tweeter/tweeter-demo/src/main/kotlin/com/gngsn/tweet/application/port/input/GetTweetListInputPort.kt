package com.gngsn.tweet.application.port.input

import com.gngsn.tweet.application.domain.model.Tweet

interface GetTweetListInputPort {
    fun get(): List<Tweet>
}