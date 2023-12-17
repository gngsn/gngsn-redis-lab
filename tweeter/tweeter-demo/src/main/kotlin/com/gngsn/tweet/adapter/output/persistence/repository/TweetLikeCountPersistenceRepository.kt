package com.gngsn.tweet.adapter.output.persistence.repository

import com.gngsn.tweet.adapter.output.persistence.entity.TweetLikeCountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TweetLikeCountPersistenceRepository : JpaRepository<TweetLikeCountEntity, Long>