package com.shop.springbootkotlinjpashop.entity

import com.shop.springbootkotlinjpashop.constant.ItemSellStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    val id: Long? = null,

    @Column(nullable = false, length = 50)
    val itemNm: String,

    @Column(name="price", nullable = false)
    val price: Int,

    @Column(nullable = false)
    val stockNumber: Int,

    @Lob
    @Column(nullable = false)
    val itemDetail: String,

    @Enumerated(EnumType.STRING)
    val itemSellStatus: ItemSellStatus,

    val regTime: LocalDateTime,

    val updateTime: LocalDateTime

)