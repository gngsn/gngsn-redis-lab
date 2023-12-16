package com.gngsn.tweet.adapter.output.persistence

import com.gngsn.tweet.adapter.output.persistence.entity.TweetEntity
import com.gngsn.tweet.adapter.output.persistence.repository.TweetRepository
import com.gngsn.tweet.application.domain.model.Tweet
import com.gngsn.tweet.application.port.output.GetTweetListOutputPort
import com.gngsn.tweet.application.port.output.SaveTweetOutputPort
import com.gngsn.tweet.support.Adapter

@Adapter
class TweetPersistenceAdapter(
    private val tweetRepository: TweetRepository
) : GetTweetListOutputPort,
    SaveTweetOutputPort {

    override fun get(): List<Tweet> {
        return tweetRepository.findAll().map { it.toDomain() }
    }

    override fun save(tweet: Tweet) {
        tweetRepository.save(TweetEntity.of(tweet))
    }
}