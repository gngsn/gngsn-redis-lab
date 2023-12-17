package com.gngsn.tweet.support.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer

@Configuration
class RedisConfiguration {

    @Value("\${spring.data.redis.host}")
    lateinit var redisHost: String

    @Value("\${spring.data.redis.port}")
    lateinit var redisPort: String

//    @Value("\${spring.data.redis.topic}")
//    lateinit var redisTopic: String

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val template: RedisTemplate<String, Any> = RedisTemplate()
        template.setConnectionFactory(jedisConnectionFactory())
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        return template
    }


    private fun jedisConnectionFactory(): JedisConnectionFactory {
        val factory = JedisConnectionFactory(
            RedisStandaloneConfiguration(redisHost, redisPort.toInt()),
            JedisClientConfiguration.builder().usePooling().build()
        )

        factory.afterPropertiesSet()
        return factory
    }

//    @Bean  TODO 알아보기
//    fun topic(): ChannelTopic = ChannelTopic(redisTopic)
//
//    @Bean
//    fun newMessageListener(): MessageListenerAdapter = MessageListenerAdapter(messageListener)
//
//    @Bean
//    fun redisContainer(): RedisMessageListenerContainer {
//        val container = RedisMessageListenerContainer()
//        container.setConnectionFactory(jedisConnectionFactory())
//        container.addMessageListener(newMessageListener(), topic())
//        return container
//    }
}