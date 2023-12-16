package com.gngsn.tweet.application.domain.usecase

import com.gngsn.tweet.application.domain.model.Tweet
import com.gngsn.tweet.application.port.input.GetTweetListInputPort
import com.gngsn.tweet.application.port.output.GetTweetListOutputPort
import com.gngsn.tweet.support.UseCase

@UseCase
class GetTweetListUseCase(
    private val getTweetListOutputPort: GetTweetListOutputPort
): GetTweetListInputPort {

    override fun get(): List<Tweet> {
        return getTweetListOutputPort.get()
    }
}