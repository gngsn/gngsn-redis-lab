package com.gngsn.voting.application.domain.model

import java.time.LocalDateTime

class UserVote(
    val id: Long? = null,
    val userId: Long,
    val voteId: Long,
    val option: Int,
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)