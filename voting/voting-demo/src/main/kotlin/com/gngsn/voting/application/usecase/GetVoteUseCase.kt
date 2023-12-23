package com.gngsn.voting.application.usecase

import com.gngsn.voting.application.domain.model.Vote
import com.gngsn.voting.application.port.input.GetVoteInputPort
import com.gngsn.voting.application.port.output.GetVoteOutputPort
import com.gngsn.voting.support.UseCase

@UseCase
class GetVoteUseCase(
    private val getVoteOutputPort: GetVoteOutputPort,
) : GetVoteInputPort {

    override fun get(command: GetVoteInputPort.Command): Vote {
        return getVoteOutputPort.get(
            GetVoteOutputPort.Query(command.voteId)
        )
    }
}