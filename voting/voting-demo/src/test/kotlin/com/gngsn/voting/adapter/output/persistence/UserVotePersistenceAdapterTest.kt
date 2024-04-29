package com.gngsn.voting.adapter.output.persistence

import com.gngsn.tweet.support.configuration.RedisConfiguration
import com.gngsn.voting.adapter.output.redis.repository.VoteOptionRedisAdapter
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.data.redis.core.RedisTemplate
import java.time.LocalDateTime
import java.util.*
import kotlin.time.Duration
import kotlin.time.measureTime

class VoteOptionRedisRepositoryTest {

    private lateinit var redisTemplate: RedisTemplate<String, Long>
    private lateinit var voteOptionRedisAdapter: VoteOptionRedisAdapter


    @BeforeEach
    fun setUp() {
        val redisProperties = RedisProperties()
        redisProperties.host = "localhost"
        redisProperties.port = 6379
        redisProperties.database = 4
        val testRedisConfiguration = RedisConfiguration(redisProperties)

        redisTemplate = testRedisConfiguration.redisTemplate() as RedisTemplate<String, Long>
        voteOptionRedisAdapter = VoteOptionRedisAdapter(redisTemplate)
    }

    @Test
//    @ExperimentalContracts
    fun compareTimesOfWhetherUsingPipelineOrNot() {

        val repeat = 3
        val intRange = 0..repeat
        val companyIds: List<Long> = intRange.map { it.toLong() }

        // Warm Up
        intRange.forEach {
            voteOptionRedisAdapter.vote(
                companyIds[it],
                Random().nextInt(3 - 0) + 0
            )
        }

        Thread.sleep(5_000)

        println("Pipelined command start : ${LocalDateTime.now()}")


        val pipelineElapsed: Duration = measureTime {
            redisTemplate.executePipelined { connection ->
                intRange.forEach {
                    voteOptionRedisAdapter.vote(
                        companyIds[it],
                        Random().nextInt(3 - 0) + 0
                    )
                }
                return@executePipelined null
            }
        }
        println("Pipelined command end : ${LocalDateTime.now()}")

        Thread.sleep(5_000)

        println("Without pipelined command start : ${LocalDateTime.now()}")
        val withoutPipelineElapsed: Duration = measureTime {
            intRange.forEach {
                voteOptionRedisAdapter.vote(
                    companyIds[it],
                    Random().nextInt(3 - 0) + 0
                )
            }
        }
        println("Without pipelined command end : ${LocalDateTime.now()}")

        Thread.sleep(5_000)

        println("pipelineElapsed : $pipelineElapsed")
        println("withoutPipelineElapsed : $withoutPipelineElapsed")
    }
}