package com.gngsn.tweet.application.usecase

import com.gngsn.tweet.application.domain.model.Tweet
import com.gngsn.tweet.application.port.input.WriteTweetInputPort
import com.gngsn.tweet.application.port.output.SaveTweetOutputPort
import com.gngsn.tweet.support.UseCase

@UseCase
class WriteTweetUseCase(
    private val saveTweetOutputPort: SaveTweetOutputPort
) : WriteTweetInputPort {

    override fun write(userId: Long, message: String) {
        return saveTweetOutputPort.save(
            Tweet(
                userId = userId,
                message = message
            )
        )
    }
}