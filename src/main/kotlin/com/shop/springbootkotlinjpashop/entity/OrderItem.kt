package com.shop.springbootkotlinjpashop.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "item_id")
    val item: Item,

    @ManyToOne
    @JoinColumn(name = "order_id")
    val order: Order?,

    val orderPrice: Int,

    val count: Int,

    val regTime: LocalDateTime,

    val updateTime: LocalDateTime
)