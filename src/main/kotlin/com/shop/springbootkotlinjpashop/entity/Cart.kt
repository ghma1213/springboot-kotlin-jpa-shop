package com.shop.springbootkotlinjpashop.entity

import jakarta.persistence.*

@Entity
class Cart(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    var id: Long? = null,

    @OneToOne
    @JoinColumn(name = "member_id")
    var member: Member
)