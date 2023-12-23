package com.gngsn.voting.adapter.output.persistence.entity

import com.gngsn.voting.application.domain.model.Vote
import com.gngsn.voting.application.domain.model.VoteOption
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "vote")
data class VoteEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name = "question")
    val question: String,

    @Column(name = "due_at")
    val dueAt: LocalDateTime,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime
) {
    constructor(o: Vote) : this(
        id = null,
        question = o.question,
        dueAt = o.dueAt,
        updatedAt = o.updatedAt
    )

    fun toDomain(options: List<VoteOption>): Vote = Vote(
        id = this.id,
        question = this.question,
        dueAt = this.dueAt,
        updatedAt = this.updatedAt,
        options = options
    )
}