package com.gngsn.voting.application.port.output

import com.gngsn.voting.application.domain.model.Voting

fun interface DoVoteFirstOutputPort {
    fun vote(voting: Voting)
}