package com.shop.springbootkotlinjpashop.entity

import com.shop.springbootkotlinjpashop.constant.OrderStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Order (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "member_id")
    val member: Member,

    val orderDate: LocalDateTime,

    @Enumerated(EnumType.STRING)
    val orderStatus: OrderStatus,

    @OneToMany
    val orderItems: List<OrderItem> = listOf(),

    val regTime: LocalDateTime,

    val updateTime: LocalDateTime

)
