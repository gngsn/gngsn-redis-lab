package com.gngsn.voting.adapter.output.persistence

import com.gngsn.voting.adapter.output.persistence.entity.UserVoteEntity
import com.gngsn.voting.adapter.output.persistence.repository.UserVotePersistenceRepository
import com.gngsn.voting.application.domain.model.UserVote
import com.gngsn.voting.application.domain.model.Voting
import com.gngsn.voting.application.port.output.GetUserVoteOutputPort
import com.gngsn.voting.application.port.output.SaveUserVoteOutputPort
import com.gngsn.voting.support.Adapter

@Adapter
class UserVotePersistenceAdapter(
    private val userVotePersistenceRepository: UserVotePersistenceRepository,
) : GetUserVoteOutputPort,
    SaveUserVoteOutputPort {

    override fun get(userId: Long, voteId: Long): UserVote? =
        userVotePersistenceRepository.findByUserIdAndVoteId(userId, voteId)?.toDomain()

    override fun save(userVoteId: Long?, vote: Voting) {
        userVotePersistenceRepository.save(
            UserVoteEntity(
                id = userVoteId,
                userId = vote.userId,
                voteId = vote.voteId,
                option = vote.option,
            )
        )
    }
}