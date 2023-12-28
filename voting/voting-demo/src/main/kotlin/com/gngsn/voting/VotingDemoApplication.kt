package com.gngsn.voting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(RedisProperties::class)
class VotingDemoApplication

fun main(args: Array<String>) {
    runApplication<VotingDemoApplication>(*args) {
        println("Kotlin Application is RUNNING!")
    }
}
