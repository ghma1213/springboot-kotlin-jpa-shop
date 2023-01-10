package com.shop.springbootkotlinjpashop.entity

import com.shop.springbootkotlinjpashop.constant.Role
import com.shop.springbootkotlinjpashop.repository.MemberRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class MemberTest {
    @Autowired
    lateinit var memberRepository: MemberRepository

    @PersistenceContext
    lateinit var em: EntityManager

    @Test
    @DisplayName("Auditing Test")
    fun auditingTest() {
        val newMember = Member(
            email = "test@email.com",
            name = "홍길동",
            address = "서울시 마포구 합정동",
            password = "1234",
            role = Role.USER
        )

        val save = memberRepository.save(newMember)

        em.flush()
        em.clear()

        val member = memberRepository.findById(save.id!!).get()
        if (member != null) {
            println("member = ${member.email}")
            println("member = ${member.regTime}")
        }
    }
}