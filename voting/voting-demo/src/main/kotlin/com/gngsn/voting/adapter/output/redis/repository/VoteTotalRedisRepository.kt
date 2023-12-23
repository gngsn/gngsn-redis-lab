package com.gngsn.voting.adapter.output.redis.repository

import com.gngsn.voting.adapter.output.redis.constant.VOTE_OPTION_KEY
import com.gngsn.voting.adapter.output.redis.constant.VOTE_TOTAL_KEY
import com.gngsn.voting.support.configuration.redis.RedisTemplateContainer
import org.springframework.stereotype.Repository


@Repository
class VoteTotalRedisRepository(
    redisTemplateCon: RedisTemplateContainer
) {
    private val operator = redisTemplateCon.DB1.opsForZSet()

    fun vote(voteId: Long) {
        operator.incrementScore(VOTE_TOTAL_KEY, voteId, 1.0)
    }

    fun get(voteId: Long): Double? = operator.score(VOTE_OPTION_KEY, voteId)
}