package com.gngsn.voting.application.usecase

import com.gngsn.voting.application.domain.model.Voting
import com.gngsn.voting.application.port.input.DoVoteInputPort
import com.gngsn.voting.application.port.output.DoRevoteOutputPort
import com.gngsn.voting.application.port.output.DoVoteFirstOutputPort
import com.gngsn.voting.application.port.output.GetUserVoteOutputPort
import com.gngsn.voting.application.port.output.SaveUserVoteOutputPort
import com.gngsn.voting.support.UseCase

@UseCase
class DoVoteUseCase(
    private val getUserVoteOutputPort: GetUserVoteOutputPort,
    private val saveUserVoteOutputPort: SaveUserVoteOutputPort,
    private val doVoteFirstOutputPort: DoVoteFirstOutputPort,
    private val doRevoteOutputPort: DoRevoteOutputPort,
) : DoVoteInputPort {

    override fun vote(voting: Voting) {
        val userVote = getUserVoteOutputPort.get(voting.userId, voting.voteId)

        if (userVote == null) {
            doVoteFirstOutputPort.vote(voting)
        } else {
            doRevoteOutputPort.revote(DoRevoteOutputPort.Query(voting, userVote.option))
        }

        saveUserVoteOutputPort.save(userVote?.id, voting)
    }
}