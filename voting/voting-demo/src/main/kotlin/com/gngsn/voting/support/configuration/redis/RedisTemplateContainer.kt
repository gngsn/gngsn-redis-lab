package com.gngsn.voting.support.configuration.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
data class RedisTemplateContainer(
    private val db0RedisTemplate: RedisTemplate<String, Any>,
    private val db1RedisTemplate: RedisTemplate<String, Any>,
) {
    val DB0: RedisTemplate<String, Any> = db0RedisTemplate
    val DB1: RedisTemplate<String, Any> = db1RedisTemplate
}
