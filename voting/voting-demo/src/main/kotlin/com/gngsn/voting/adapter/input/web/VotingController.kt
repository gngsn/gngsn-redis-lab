package com.gngsn.voting.adapter.input.web

import com.gngsn.voting.application.domain.model.Vote
import com.gngsn.voting.application.domain.model.Voting
import com.gngsn.voting.application.port.input.DoVoteInputPort
import com.gngsn.voting.application.port.input.GetVoteInputPort
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/vote")
class VotingController(
    private val getVoteInputPort: GetVoteInputPort,
    private val doVoteInputPort: DoVoteInputPort,
) {

    @GetMapping
    fun get(voteId: Long): Vote {
        return getVoteInputPort.get(GetVoteInputPort.Command(voteId))
    }

    @PostMapping
    fun vote(@RequestBody voting: Voting) {
        doVoteInputPort.vote(voting)
    }
}