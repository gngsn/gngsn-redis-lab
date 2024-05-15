package com.gngsn.tweet.adapter.output.redis

import org.springframework.data.redis.core.ZSetOperations

data class ScoreValue(
    val value: Long,
    val score: Double,
) : Comparable<ScoreValue> {
    companion object {
        fun of(o: ZSetOperations.TypedTuple<Any>): ScoreValue =
            ScoreValue(
                (o.value as Int).toLong(),
                o.score ?: throw IllegalArgumentException(),
            )
    }

    override fun compareTo(other: ScoreValue): Int {
        return this.score.compareTo(other.score)
    }
}