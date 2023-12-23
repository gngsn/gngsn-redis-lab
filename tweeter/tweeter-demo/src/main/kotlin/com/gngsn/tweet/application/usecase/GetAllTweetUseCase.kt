package com.gngsn.tweet.application.usecase

import com.gngsn.tweet.application.domain.model.Tweet
import com.gngsn.tweet.application.port.input.GetAllTweetInputPort
import com.gngsn.tweet.application.port.output.GetAllTweetOutputPort
import com.gngsn.tweet.support.UseCase

@UseCase
class GetAllTweetUseCase(
    private val getAllTweetOutputPort: GetAllTweetOutputPort,
    private val getAllTweetLikeOutputPort: GetAllTweetOutputPort,
) : GetAllTweetInputPort {

    override fun get(): List<Tweet> {
        return getAllTweetOutputPort.get()
    }
}