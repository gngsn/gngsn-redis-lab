package com.gngsn.voting.application.port.output

import com.gngsn.voting.application.domain.model.Voting

interface SaveUserVoteOutputPort {
    fun save(userVoteId: Long?, vote: Voting)
}