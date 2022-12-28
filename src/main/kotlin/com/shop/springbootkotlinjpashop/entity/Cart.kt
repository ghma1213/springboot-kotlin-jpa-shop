package com.shop.springbootkotlinjpashop.entity

import jakarta.persistence.*

@Entity
data class Cart(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,

    @OneToOne
    @JoinColumn(name = "member_id")
    val member: Member
)