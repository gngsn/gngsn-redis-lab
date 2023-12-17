package com.gngsn.tweet.adapter.input.web

import com.gngsn.tweet.application.domain.model.Tweet
import com.gngsn.tweet.application.port.input.GetAllTweetInputPort
import com.gngsn.tweet.application.port.input.LikeTweetInputPort
import com.gngsn.tweet.application.port.input.WriteTweetInputPort
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/tweet")
class TweetController(
    private val getAllTweetInputPort: GetAllTweetInputPort,
    private val writeTweetInputPort: WriteTweetInputPort,
    private val likeTweetLikeTweetInputPort: LikeTweetInputPort,
) {

    @GetMapping
    fun getList(): List<Tweet> {
        return getAllTweetInputPort.get()
    }

    @PostMapping
    fun write(@RequestBody dto: WriteDto) {
        writeTweetInputPort.write(dto.userId, dto.message)
    }

    @PostMapping("/like/{tweetId}")
    fun like(@PathVariable tweetId: Long, @RequestHeader userId: Long) {
        likeTweetLikeTweetInputPort.like(tweetId, userId)
    }

    data class WriteDto(
        val userId: Long,
        val message: String
    )
}