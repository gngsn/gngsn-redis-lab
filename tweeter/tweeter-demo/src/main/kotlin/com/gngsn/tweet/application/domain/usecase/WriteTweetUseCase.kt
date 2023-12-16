package com.gngsn.tweet.application.domain.usecase

import com.gngsn.tweet.application.domain.model.Tweet
import com.gngsn.tweet.application.port.input.GetTweetListInputPort
import com.gngsn.tweet.application.port.input.WriteTweetInputPort
import com.gngsn.tweet.application.port.output.GetTweetListOutputPort
import com.gngsn.tweet.application.port.output.SaveTweetOutputPort
import com.gngsn.tweet.support.UseCase

@UseCase
class WriteTweetUseCase(
    private val saveTweetOutputPort: SaveTweetOutputPort
): WriteTweetInputPort {

    override fun write(tweet: Tweet) {
        return saveTweetOutputPort.save(tweet)
    }
}