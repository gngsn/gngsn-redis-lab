package com.gngsn.tweet.adapter.output.redis.repository

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.redis.connection.RedisConnection
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.transaction.support.TransactionSynchronizationManager


@ExtendWith(MockitoExtension::class)
class TweetUvRedisRepositoryTest {

    lateinit var template: RedisTemplate<String, Long>

    @Mock
    lateinit var connectionFactoryMock: RedisConnectionFactory

    @Mock
    lateinit var redisConnectionMock: RedisConnection
    lateinit var tweetUvRedisRepository: TweetUvRedisRepository


    @BeforeEach
    fun setUp() {
        TransactionSynchronizationManager.clear()

        template = RedisTemplate<String, Long>()
        template.setConnectionFactory(connectionFactoryMock)
        `when`(connectionFactoryMock.connection).thenReturn(redisConnectionMock)

        template.afterPropertiesSet()

        tweetUvRedisRepository = TweetUvRedisRepository(template)
    }


    @Test
    fun view() {
        { tweetUvRedisRepository.view(12L) }.repeat(5);
        { tweetUvRedisRepository.view(213L) }.repeat(2);
        { tweetUvRedisRepository.view(145L) }.repeat(10);
        { tweetUvRedisRepository.view(132L) }.repeat(14);
        { tweetUvRedisRepository.view(142L) }.repeat(44);
        { tweetUvRedisRepository.view(42L) }.repeat(20)

        val top = tweetUvRedisRepository.top(3)

        assertEquals(top.map { it.value }, listOf(142L, 42L, 145L))
    }

    @Test
    fun top() {
    }


    fun (() -> Unit).repeat(n: Int) = (1..n).forEach { _ -> this() }
}