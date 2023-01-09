package com.shop.springbootkotlinjpashop.repository

import com.shop.springbootkotlinjpashop.entity.Order
import org.springframework.data.repository.CrudRepository

interface OrderRepository: CrudRepository<Order, Long> {
}