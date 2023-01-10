package com.shop.springbootkotlinjpashop.repository

import com.shop.springbootkotlinjpashop.entity.OrderItem
import org.springframework.data.repository.CrudRepository

interface OrderItemRepository: CrudRepository<OrderItem, Long> {
}