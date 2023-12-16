package com.gngsn.tweet.adapter.input.web

import com.gngsn.tweet.application.domain.model.Tweet
import com.gngsn.tweet.application.port.input.GetTweetListInputPort
import com.gngsn.tweet.application.port.input.WriteTweetInputPort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/tweet")
class TweetController(
    private val getTweetListInputPort: GetTweetListInputPort,
    private val writeTweetInputPort: WriteTweetInputPort
) {

    @GetMapping
    fun getList(): List<Tweet> {
        return getTweetListInputPort.get()
    }

    @PostMapping
    fun write(tweet: Tweet) {
        writeTweetInputPort.write(tweet)
    }
}