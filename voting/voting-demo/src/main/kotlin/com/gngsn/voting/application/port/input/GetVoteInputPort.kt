package com.gngsn.voting.application.port.input

import com.gngsn.voting.application.domain.model.Vote

interface GetVoteInputPort {
    fun get(command: Command): Vote

    data class Command(
        val voteId: Long
    )
}