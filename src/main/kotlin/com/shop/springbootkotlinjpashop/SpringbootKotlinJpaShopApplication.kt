package com.shop.springbootkotlinjpashop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class SpringbootKotlinJpaShopApplication

fun main(args: Array<String>) {
    runApplication<SpringbootKotlinJpaShopApplication>(*args)
}
