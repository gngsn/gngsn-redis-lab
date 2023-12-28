package com.gngsn.voting.support.configuration.redis

//@ConfigurationProperties(prefix = "spring.data.redis")
//data class RedisProperties(
//    val host: String,
//    val port: String,
//    val database: String = "0"
//)
//
//@Configuration
//class DB1RedisConfiguration {
//
//    @Value("\${spring.data.redis.host}")
//    lateinit var host: String
//
//    @Value("\${spring.data.redis.port}")
//    lateinit var port: String
//    val database: Int = 0
//
//    @Bean
//    fun db1jedisConnectionFactory(): JedisConnectionFactory {
//        val redisStandaloneConfiguration = RedisStandaloneConfiguration()
//        redisStandaloneConfiguration.hostName = host
//        redisStandaloneConfiguration.port = port.toInt()
//        redisStandaloneConfiguration.database = database
//        return JedisConnectionFactory(redisStandaloneConfiguration)
//    }
//
//    @Bean
//    fun db1RedisConnectionFactory(): RedisConnectionFactory {
//        return db1jedisConnectionFactory()
//    }
//
//    @Bean
//    @Qualifier("db1RedisTemplate")
//    fun db1RedisTemplate(): RedisTemplate<String, Any> {
//        val template = RedisTemplate<String, Any>()
//        template.setConnectionFactory(db1RedisConnectionFactory())
//
//        template.keySerializer = StringRedisSerializer()
//        template.valueSerializer = GenericJackson2JsonRedisSerializer()
//        template.hashKeySerializer = StringRedisSerializer()
//        template.hashValueSerializer = GenericJackson2JsonRedisSerializer()
//        template.afterPropertiesSet()
//        return template
//    }
//}
