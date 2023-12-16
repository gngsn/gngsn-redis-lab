package com.gngsn.tweet.application.port.output

import com.gngsn.tweet.application.domain.model.Tweet

interface SaveTweetOutputPort {
    fun save(tweet: Tweet)
}