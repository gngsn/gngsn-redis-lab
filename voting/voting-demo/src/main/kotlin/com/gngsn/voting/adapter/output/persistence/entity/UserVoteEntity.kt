package com.gngsn.voting.adapter.output.persistence.entity

import com.gngsn.voting.application.domain.model.UserVote
import com.gngsn.voting.application.domain.model.Voting
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "user_vote")
data class UserVoteEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "vote_id")
    val voteId: Long,

    @Column(name = "option")
    val option: Int,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    constructor(
        userId: Long,
        voteId: Long,
        option: Int,
    ) : this(
        id = null,
        userId = userId,
        voteId = voteId,
        option = option,
    )

    constructor(
        v: Voting
    ) : this(
        id = null,
        userId = v.userId,
        voteId = v.voteId,
        option = v.option,
    )

    fun toDomain(): UserVote = UserVote(
        id = id,
        userId = userId,
        voteId = voteId,
        option = option,
        updatedAt = updatedAt,
    )
}