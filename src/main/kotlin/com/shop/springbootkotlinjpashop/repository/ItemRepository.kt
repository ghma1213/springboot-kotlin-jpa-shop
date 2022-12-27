package com.shop.springbootkotlinjpashop.repository

import com.shop.springbootkotlinjpashop.entity.Item
import org.springframework.data.repository.CrudRepository

interface ItemRepository: CrudRepository<Item, Long> {
    fun findByItemNm(itemNm: String): List<Item>
    fun findByItemNmOrItemDetail(iteNm: String, itemDetail: String): List<Item>
    fun findByPriceLessThan(price: Int): List<Item>
    fun findByPriceLessThanOrderByPriceDesc(price: Int): List<Item>
}