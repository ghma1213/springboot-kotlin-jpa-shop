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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    val orderDate: LocalDateTime,

    @Enumerated(EnumType.STRING)
    val orderStatus: OrderStatus,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val orderItems: List<OrderItem> = listOf(),

): BaseEntity()