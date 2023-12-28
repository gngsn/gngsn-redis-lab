package com.gngsn.voting.adapter.output.persistence

import com.gngsn.voting.adapter.output.persistence.entity.UserVoteEntity
import com.gngsn.voting.adapter.output.persistence.repository.UserVotePersistenceRepository
import com.gngsn.voting.adapter.output.persistence.repository.VotePersistenceRepository
import com.gngsn.voting.adapter.output.redis.repository.VoteOptionRedisAdapter
import com.gngsn.voting.adapter.output.redis.repository.VoteTotalRedisRepository
import com.gngsn.voting.application.domain.model.Vote
import com.gngsn.voting.application.domain.model.VoteOption
import com.gngsn.voting.application.domain.model.Voting
import com.gngsn.voting.application.port.output.DoRevoteOutputPort
import com.gngsn.voting.application.port.output.DoVoteFirstOutputPort
import com.gngsn.voting.application.port.output.GetVoteOutputPort
import com.gngsn.voting.support.Adapter

@Adapter
class VotingPersistenceAdapter(
    private val userVotePersistenceRepository: UserVotePersistenceRepository,
    private val votePersistenceRepository: VotePersistenceRepository,
    private val voteTotalRedisRepository: VoteTotalRedisRepository,
    private val voteOptionRedisAdapter: VoteOptionRedisAdapter,
) : GetVoteOutputPort,
    DoVoteFirstOutputPort,
    DoRevoteOutputPort {

    override fun get(q: GetVoteOutputPort.Query): Vote {
        val vote = votePersistenceRepository.findById(q.voteId).get()
        val scoreValues = voteOptionRedisAdapter.findAll(q.voteId)

        return vote.toDomain(scoreValues.map {
            VoteOption(vote.id!!, it.option.toInt(), it.score)
        })
    }

    override fun vote(voting: Voting) {
        voteOptionRedisAdapter.vote(voting.voteId, voting.option)
        voteTotalRedisRepository.vote(voting.voteId)
        userVotePersistenceRepository.save(UserVoteEntity(voting))
    }

    override fun revote(q: DoRevoteOutputPort.Query) {
        voteOptionRedisAdapter.change(q)
        userVotePersistenceRepository.save(UserVoteEntity(q.userId, q.voteId, q.newOption))
    }
}