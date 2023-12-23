package com.gngsn.voting.adapter.output.persistence.repository

import com.gngsn.voting.adapter.output.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserPersistenceRepository : JpaRepository<UserEntity, Long>