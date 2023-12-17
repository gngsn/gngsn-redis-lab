package com.gngsn.tweet.adapter.output.persistence.repository

import com.gngsn.tweet.adapter.output.persistence.entity.TweetLikeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TweetLikePersistenceRepository : JpaRepository<TweetLikeEntity, Long>