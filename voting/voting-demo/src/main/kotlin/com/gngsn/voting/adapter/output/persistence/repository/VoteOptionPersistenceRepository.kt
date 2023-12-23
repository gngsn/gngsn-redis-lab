package com.gngsn.voting.adapter.output.persistence.repository

import com.gngsn.voting.adapter.output.persistence.entity.VoteOptionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface VoteOptionPersistenceRepository : JpaRepository<VoteOptionEntity, Long> {
    fun findAllByVoteId(voteId: Long): List<VoteOptionEntity>
}