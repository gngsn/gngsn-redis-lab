package com.gngsn.voting.application.domain.model

import java.time.LocalDateTime

class Vote(
    val id: Long? = null,
    val question: String,
    val dueAt: LocalDateTime,
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val options: List<VoteOption>? = null,
)