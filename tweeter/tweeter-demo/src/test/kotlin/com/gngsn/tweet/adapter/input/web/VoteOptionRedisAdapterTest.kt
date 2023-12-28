package com.gngsn.tweet.adapter.input.web

import com.gngsn.tweet.support.configuration.RedisConfiguration
import com.gngsn.voting.adapter.output.redis.repository.VoteOptionRedisAdapter
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.support.collections.RedisProperties
import java.time.LocalDateTime
import kotlin.time.Duration
import kotlin.time.measureTime


class VoteOptionRedisAdapterTest {

    private lateinit var redisTemplate: RedisTemplate<String, String>
    private lateinit var voteOptionRedisAdapter: VoteOptionRedisAdapter


    @BeforeEach
    fun setUp() {
        val testRedisConfiguration = RedisConfiguration(RedisProperties("localhost", "6379", "10"))
        val lettuceConnectionFactory = testRedisConfiguration.lettuceConnectionFactory()
        lettuceConnectionFactory.start()

        redisTemplate = testRedisConfiguration.redisTemplate(lettuceConnectionFactory)
        voteOptionRedisAdapter = VoteOptionRedisAdapter(redisTemplate)
    }

    @Test
//    @ExperimentalContracts
    fun compareTimesOfWhetherUsingPipelineOrNot() {

        val repeat = 10_000
        val intRange = 0..repeat
        val companyIds: List<Long> = intRange.map { Fairy.create().baseProducer().randomBetween(0, 1000).toLong() }

        // Warm Up
        intRange.forEach {
            voteOptionRedisAdapter.vote(
                companyIds[it],
                Fairy.create().baseProducer().randomBetween(0, 3)
            )
        }

        Thread.sleep(5_000)

        println("Pipelined command start : ${LocalDateTime.now()}")


        val pipelineElapsed: Duration = measureTime {
            redisTemplate.executePipelined { connection ->
                intRange.forEach {
                    voteOptionRedisAdapter.vote(
                        companyIds[it],
                        Fairy.create().baseProducer().randomBetween(0, 3)
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
                    Fairy.create().baseProducer().randomBetween(0, 3)
                )
            }
        }
        println("Without pipelined command end : ${LocalDateTime.now()}")

        Thread.sleep(5_000)

        println("pipelineElapsed : $pipelineElapsed")
        println("withoutPipelineElapsed : $withoutPipelineElapsed")

        intRange.forEach {
            redisTemplate.delete(
                voteOptionRedisAdapter.voteKey(companyIds[it]),
            )
        }
    }
}