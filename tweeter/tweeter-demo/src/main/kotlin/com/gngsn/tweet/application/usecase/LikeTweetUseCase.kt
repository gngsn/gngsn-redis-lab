package com.gngsn.tweet.application.usecase

import com.gngsn.tweet.application.port.input.LikeTweetInputPort
import com.gngsn.tweet.application.port.output.SaveTweetLikeOutputPort
import com.gngsn.tweet.support.UseCase

@UseCase
class LikeTweetUseCase(
    private val saveTweetLikeOutputPort: SaveTweetLikeOutputPort
) : LikeTweetInputPort {

    override fun like(tweetId: Long, userId: Long) {
        return saveTweetLikeOutputPort.save(tweetId, userId)
    }
}