package com.shop.springbootkotlinjpashop.repository

import com.shop.springbootkotlinjpashop.entity.Cart
import org.springframework.data.repository.CrudRepository

interface CartRepository: CrudRepository<Cart, Long> {

}
