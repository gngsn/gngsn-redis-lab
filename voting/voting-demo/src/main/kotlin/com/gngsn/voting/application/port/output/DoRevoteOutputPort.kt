package com.gngsn.voting.application.port.output

import com.gngsn.voting.application.domain.model.Voting

interface DoRevoteOutputPort {
    fun revote(query: Query)

    data class Query(
        val userId: Long,
        val voteId: Long,
        val prevOption: Int,
        val newOption: Int,
    ) {
        constructor(voting: Voting, prevOption: Int) : this(
            userId = voting.userId,
            voteId = voting.voteId,
            prevOption = prevOption,
            newOption = voting.option
        )
    }
}