package com.shop.springbootkotlinjpashop.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class CartItem (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "cart_id")
    val cart: Cart,

    @ManyToOne
    @JoinColumn(name = "item_id")
    val item: Item,

    val count: Int
)