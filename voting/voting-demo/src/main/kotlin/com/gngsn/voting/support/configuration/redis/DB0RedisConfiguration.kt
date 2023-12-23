package com.gngsn.voting.support.configuration.redis

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


//@Configuration
//class DB0RedisConfiguration {
//
//    @Value("\${spring.data.redis.host}")
//    lateinit var host: String
//
//    @Value("\${spring.data.redis.port}")
//    lateinit var port: String
//
//    @Bean
//    fun redisTemplateContainer(): RedisTemplateContainer {
//        val redisTemplateContainer = RedisTemplateContainer(
//            (0..1).map {
//                db0RedisTemplate()
//            }
//        )
//        return redisTemplateContainer
//    }
//
//    @Bean
//    @Qualifier("DB0RedisTemplate")
//    fun db0RedisTemplate(): RedisTemplate<String, Any> {
//        return redisTemplate(db0jedisConnectionFactory())
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
//    fun db0jedisConnectionFactory(): JedisConnectionFactory {
//        val redisStandaloneConfiguration = RedisStandaloneConfiguration()
//        redisStandaloneConfiguration.hostName = host
//        redisStandaloneConfiguration.port = port.toInt()
//        redisStandaloneConfiguration.database = 0
//        return JedisConnectionFactory(redisStandaloneConfiguration)
//    }
//}

@Configuration
class DB0RedisConfiguration {

    @Value("\${spring.data.redis.host}")
    lateinit var host: String

    @Value("\${spring.data.redis.port}")
    lateinit var port: String
    val database: Int = 0

//    @Bean
//    fun redisTemplateContainer(): RedisTemplateContainer {
//        val redisTemplateContainer = RedisTemplateContainer(
//            (0..1).map {
//                db0RedisTemplate()
//            }
//        )
//        return redisTemplateContainer
//    }

    @Bean
    fun db0jedisConnectionFactory(): JedisConnectionFactory {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration()
        redisStandaloneConfiguration.hostName = host
        redisStandaloneConfiguration.port = port.toInt()
        redisStandaloneConfiguration.database = database
        return JedisConnectionFactory(redisStandaloneConfiguration)
    }

    @Bean
    @Primary
    fun db0RedisConnectionFactory(): RedisConnectionFactory {
        return db0jedisConnectionFactory()
    }

    @Bean
    @Qualifier("db0RedisTemplate")
    fun db0RedisTemplate(): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.setConnectionFactory(db0RedisConnectionFactory())

        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = GenericJackson2JsonRedisSerializer()
        template.afterPropertiesSet()
        return template
    }
}
