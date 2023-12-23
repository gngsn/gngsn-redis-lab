package com.gngsn.voting.adapter.output.persistence.entity

import com.gngsn.voting.application.domain.model.VoteOption
import jakarta.persistence.*


@Entity
@Table(name = "vote_option")
data class VoteOptionEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "vote_id")
    val voteId: Long,

    @Column(name = "option")
    val option: Int,

    @Column(name = "score")
    val score: Long
) {
    fun toDomain(): VoteOption = VoteOption(
        id = this.id,
        voteId = this.voteId,
        option = this.option,
        score = this.score,
    )
}
