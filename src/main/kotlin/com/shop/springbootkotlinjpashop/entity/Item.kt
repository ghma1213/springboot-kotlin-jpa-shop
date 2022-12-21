package com.shop.springbootkotlinjpashop.entity

import com.shop.springbootkotlinjpashop.constant.ItemSellStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id", nullable = false)
    var id: Long? = null,

    @Column(nullable = false, length = 50)
    var itemNm: String,

    @Column(name="price", nullable = false)
    var price: Int,

    @Column(nullable = false)
    var stockNumber: Int,

    @Lob
    @Column(nullable = false)
    var itemDetail: String,

    @Enumerated(EnumType.STRING)
    var itemSellStatus: ItemSellStatus,

    var regTime: LocalDateTime,

    var updateTime: LocalDateTime

)