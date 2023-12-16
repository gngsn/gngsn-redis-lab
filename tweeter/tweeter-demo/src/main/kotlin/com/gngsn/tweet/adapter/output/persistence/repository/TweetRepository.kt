package com.gngsn.tweet.adapter.output.persistence.repository

import com.gngsn.tweet.adapter.output.persistence.entity.TweetEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TweetRepository : JpaRepository<TweetEntity, Long>