package com.gngsn.voting.application.domain.model


class VoteOption(
    val id: Long? = null,
    val voteId: Long,
    val option: Int,
    val score: Long,
) {
    constructor(voteId: Long, option: Int, score: Long) : this(
        id = null,
        voteId = voteId,
        option = option,
        score = score,
    )
}
