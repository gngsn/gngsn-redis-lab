package com.gngsn.voting.application.port.input

import com.gngsn.voting.application.domain.model.Voting

interface DoVoteInputPort {
    fun vote(voting: Voting)
}