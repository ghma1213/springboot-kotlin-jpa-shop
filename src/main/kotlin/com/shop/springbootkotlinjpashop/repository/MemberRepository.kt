package com.shop.springbootkotlinjpashop.repository

import com.shop.springbootkotlinjpashop.entity.Member
import org.springframework.data.repository.CrudRepository

interface MemberRepository: CrudRepository<Member, Long> {
    fun findByEmail(email: String): Member?
}
