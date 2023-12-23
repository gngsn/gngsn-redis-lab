package com.gngsn.voting.adapter.output.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user")
data class UserEntity(

    @Id
    @Column(name = "id")
    val id: Long,

    @Column(name = "name")
    val name: String,
)
