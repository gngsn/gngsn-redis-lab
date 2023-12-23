package com.gngsn.voting.adapter.output.persistence.repository

import com.gngsn.voting.adapter.output.persistence.entity.VoteEntity
import org.springframework.data.jpa.repository.JpaRepository

interface VotePersistenceRepository : JpaRepository<VoteEntity, Long>