package com.gngsn.tweet.adapter.input.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.gngsn.tweet.application.port.input.GetAllTweetInputPort
import com.gngsn.tweet.application.port.input.LikeTweetInputPort
import com.gngsn.tweet.application.port.input.WriteTweetInputPort
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [TweetController::class])
//@SpringBootTest
class TweetControllerTest(@Autowired val mockMvc: MockMvc) {

    lateinit var tweetController: TweetController

    @MockBean
    lateinit var getAllTweetInputPort: GetAllTweetInputPort

    @MockBean
    lateinit var writeTweetInputPort: WriteTweetInputPort

    @MockBean
    lateinit var likeTweetLikeTweetInputPort: LikeTweetInputPort

    val objectMapper = ObjectMapper().registerModules(JavaTimeModule())

    @Test
    fun getList() {
    }

    @Test
    fun write() {
        mockMvc.post("/api/v1/tweet") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(DTO(userId = 12, message = "hiihhi"))
        }.andExpect {
            status().isOk
            content().contentType(MediaType.APPLICATION_JSON)
        }
    }

    data class DTO(
        val userId: Long,
        val message: String
    )
}