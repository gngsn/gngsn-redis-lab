package com.gngsn.voting.adapter.output.redis.repository

import com.gngsn.voting.adapter.output.redis.constant.UNVOTE
import com.gngsn.voting.adapter.output.redis.constant.VOTE
import com.gngsn.voting.adapter.output.redis.constant.VOTE_OPTION_KEY
import com.gngsn.voting.application.port.output.DoRevoteOutputPort
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository


@Repository
class VoteOptionRedisAdapter(
//    redisTemplateCon: RedisTemplateContainer
    redisTemplate: RedisTemplate<String, Long>
) {
    private val operator = redisTemplate.opsForHash<String, Long>()

    fun vote(voteId: Long, optionId: Int) {
        operator.increment(voteKey(voteId), voteHashKey(optionId), VOTE)
    }

    fun change(q: DoRevoteOutputPort.Query) {
        operator.increment(voteKey(q.voteId), voteHashKey(q.prevOption), VOTE)
        operator.increment(voteKey(q.voteId), voteHashKey(q.newOption), UNVOTE)
    }

    fun findById(voteId: Long, optionId: Int): Long? =
        operator.get(voteKey(voteId), voteHashKey(optionId))

    fun findAll(voteId: Long): List<OptionScore> {
        return operator.entries(voteKey(voteId)).map { OptionScore(it.key, it.value) }
    }

    private fun voteKey(voteId: Long) = "$VOTE_OPTION_KEY$voteId"
    private fun voteHashKey(optionId: Int) = "$optionId"

    data class OptionScore(
        val option: String,
        val score: Long,
    )
}