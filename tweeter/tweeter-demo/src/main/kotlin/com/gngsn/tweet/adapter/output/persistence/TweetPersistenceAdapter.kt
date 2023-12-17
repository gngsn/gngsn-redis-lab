package com.gngsn.tweet.adapter.output.persistence

import com.gngsn.tweet.adapter.output.persistence.entity.TweetEntity
import com.gngsn.tweet.adapter.output.persistence.entity.TweetLikeEntity
import com.gngsn.tweet.adapter.output.persistence.repository.TweetLikePersistenceRepository
import com.gngsn.tweet.adapter.output.persistence.repository.TweetPersistenceRepository
import com.gngsn.tweet.adapter.output.redis.repository.TweetLikeCountRedisRepository
import com.gngsn.tweet.application.domain.model.Tweet
import com.gngsn.tweet.application.port.output.GetAllTweetOutputPort
import com.gngsn.tweet.application.port.output.SaveTweetLikeOutputPort
import com.gngsn.tweet.application.port.output.SaveTweetOutputPort
import com.gngsn.tweet.support.Adapter

@Adapter
class TweetPersistenceAdapter(
    private val tweetPersistenceRepository: TweetPersistenceRepository,
    private val tweetLikePersistenceRepository: TweetLikePersistenceRepository,
    private val tweetLikeCountRedisRepository: TweetLikeCountRedisRepository,
) : GetAllTweetOutputPort,
    SaveTweetOutputPort,
    SaveTweetLikeOutputPort {

    override fun get(): List<Tweet> {
        val likeMap = tweetLikeCountRedisRepository.findAll().associate { it.value to it.score }

        return tweetPersistenceRepository.findAll()
            .map { it.toDomain(likeMap.getOrDefault(it.id, 0.0).toLong()) }
    }

    override fun save(tweet: Tweet) {
        tweetPersistenceRepository.save(TweetEntity.of(tweet))
    }

    override fun save(tweetId: Long, userId: Long) {
        tweetLikePersistenceRepository.save(TweetLikeEntity(tweetId, userId))
        tweetLikeCountRedisRepository.zadd(tweetId, userId.toDouble())
    }
}