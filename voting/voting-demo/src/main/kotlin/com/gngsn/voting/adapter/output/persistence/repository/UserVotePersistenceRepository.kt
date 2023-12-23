package com.gngsn.voting.adapter.output.persistence.repository

import com.gngsn.voting.adapter.output.persistence.entity.UserVoteEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserVotePersistenceRepository : JpaRepository<UserVoteEntity, Long> {
    fun findByUserIdAndVoteId(userId: Long, voteId: Long): UserVoteEntity?
}