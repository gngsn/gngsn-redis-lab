package com.gngsn.tweet.support.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisConfiguration {

    @Value("\${spring.data.redis.host}")
    lateinit var redisHost: String

    @Value("\${spring.data.redis.port}")
    lateinit var redisPort: String

    @Bean
    fun db01redisTemplate(): RedisTemplate<String, Any> {
        val template: RedisTemplate<String, Any> = RedisTemplate()
        template.setConnectionFactory(jedisConnectionFactory(redisHost, redisPort.toInt(), 0))

        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = GenericJackson2JsonRedisSerializer()

        return template
    }

    @Bean
    fun db02redisTemplate(): RedisTemplate<String, Long> {
        val template: RedisTemplate<String, Long> = RedisTemplate()
        template.setConnectionFactory(jedisConnectionFactory(redisHost, redisPort.toInt(), 1))

        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = GenericJackson2JsonRedisSerializer()

        return template
    }


    private fun jedisConnectionFactory(host: String, port: Int, database: Int): LettuceConnectionFactory {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration(host, port)
        redisStandaloneConfiguration.database = database

        val factory = LettuceConnectionFactory(
            redisStandaloneConfiguration,
            LettuceClientConfiguration.builder().build()
        )

        factory.afterPropertiesSet()
        return factory
    }
}