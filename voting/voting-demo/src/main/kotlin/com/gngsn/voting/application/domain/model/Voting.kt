package com.gngsn.voting.application.domain.model

data class Voting(
    val userId: Long,
    val voteId: Long,
    val option: Int
)