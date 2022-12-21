package com.shop.springbootkotlinjpashop

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @GetMapping("/test")
    fun test(): UserDto {
        val userDto = UserDto("MA", 20)

        return userDto
    }
}