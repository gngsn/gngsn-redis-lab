package com.gngsn.voting.application.port.output

import com.gngsn.voting.application.domain.model.UserVote

fun interface GetUserVoteOutputPort {
    fun get(userId: Long, voteId: Long): UserVote?
}