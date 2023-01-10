package com.shop.springbootkotlinjpashop.entity

import jakarta.persistence.*

@Entity
class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    val item: Item,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Order?,

    val orderPrice: Int,

    val count: Int,
): BaseEntity()