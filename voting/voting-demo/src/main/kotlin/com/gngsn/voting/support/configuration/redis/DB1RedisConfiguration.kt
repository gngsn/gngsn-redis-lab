package com.gngsn.voting.support.configuration.redis

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@ConfigurationProperties(prefix = "spring.data.redis")
data class RedisProperties(
    val host: String,
    val port: String
)

//@Configuration
//class DB1RedisConfiguration(
//    val redisProperties: RedisProperties
//) {
//    @Bean
//    @Qualifier("DB1RedisTemplate")
//    fun db1RedisTemplate(): RedisTemplate<String, Any> {
//        return redisTemplate(db1jedisConnectionFactory())
//    }
//
//    private fun redisTemplate(connectionFactory: JedisConnectionFactory): RedisTemplate<String, Any> {
//        val template: RedisTemplate<String, Any> = RedisTemplate()
//        template.keySerializer = StringRedisSerializer()
//        template.valueSerializer = GenericJackson2JsonRedisSerializer()
//        template.hashKeySerializer = StringRedisSerializer()
//        template.hashValueSerializer = GenericJackson2JsonRedisSerializer()
//        template.setConnectionFactory(connectionFactory)
//        template.afterPropertiesSet()
//        return template
//    }
//
//    @Bean
//    fun db1jedisConnectionFactory(): JedisConnectionFactory {
//        val redisStandaloneConfiguration = RedisStandaloneConfiguration()
//        redisStandaloneConfiguration.hostName = redisProperties.host
//        redisStandaloneConfiguration.port = redisProperties.port.toInt()
//        redisStandaloneConfiguration.database = 1
//
//        val factory = JedisConnectionFactory(redisStandaloneConfiguration)
//        return factory
//    }
//}

@Configuration
class DB1RedisConfiguration {

    @Value("\${spring.data.redis.host}")
    lateinit var host: String

    @Value("\${spring.data.redis.port}")
    lateinit var port: String
    val database: Int = 0

    @Bean
    fun db1jedisConnectionFactory(): JedisConnectionFactory {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration()
        redisStandaloneConfiguration.hostName = host
        redisStandaloneConfiguration.port = port.toInt()
        redisStandaloneConfiguration.database = database
        return JedisConnectionFactory(redisStandaloneConfiguration)
    }

    @Bean
    fun db1RedisConnectionFactory(): RedisConnectionFactory {
        return db1jedisConnectionFactory()
    }

    @Bean
    @Qualifier("db1RedisTemplate")
    fun db1RedisTemplate(): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.setConnectionFactory(db1RedisConnectionFactory())

        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = GenericJackson2JsonRedisSerializer()
        template.afterPropertiesSet()
        return template
    }
}
