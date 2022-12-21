package com.shop.springbootkotlinjpashop.repository

import com.shop.springbootkotlinjpashop.entity.Item
import org.springframework.data.repository.CrudRepository

interface ItemRepository: CrudRepository<Item, Long> {

}