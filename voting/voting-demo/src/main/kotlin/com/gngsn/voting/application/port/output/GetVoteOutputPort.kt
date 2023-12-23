package com.gngsn.voting.application.port.output

import com.gngsn.voting.application.domain.model.Vote
import java.time.LocalDateTime

fun interface GetVoteOutputPort {
    fun get(q: Query): Vote

    data class Query(
        val voteId: Long,
        val now: LocalDateTime = LocalDateTime.now()
    )
}