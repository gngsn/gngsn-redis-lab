package com.gngsn.tweet.application.domain.usecase

import com.gngsn.tweet.application.port.input.DeleteTweetInputPort
import com.gngsn.tweet.application.port.output.DeleteTweetOutputPort
import com.gngsn.tweet.support.UseCase

@UseCase
class DeleteTweetUseCase(
    private val deleteTweetOutputPort: DeleteTweetOutputPort
) : DeleteTweetInputPort {

    override fun delete(tweetId: Long) {
        deleteTweetOutputPort.delete(tweetId)
    }
}