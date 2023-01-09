package com.shop.springbootkotlinjpashop.entity

import com.shop.springbootkotlinjpashop.dto.MemberFormDto
import com.shop.springbootkotlinjpashop.repository.CartRepository
import com.shop.springbootkotlinjpashop.repository.MemberRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.TestPropertySource
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@TestPropertySource(locations = ["classpath:application-test.yml"])
class CartTest {
    @Autowired
    lateinit var cartRepository: CartRepository

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @PersistenceContext
    lateinit var em: EntityManager

    fun createMember(): Member {
        val memberFormDto = MemberFormDto(
            "마경환",
            "ttt@test.com",
            "1234",
            "서울시 마포구 합정동"
        )
        return Member.createMember(memberFormDto, passwordEncoder)
    }

    @Test
    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    fun findCartAndMemberTest() {
        val member = createMember()
        memberRepository.save(member)

        val cart = Cart(
            member = member
        )
        cartRepository.save(cart)

        em.flush()
        em.clear()
        val savedCart = cart.id?.let { cartRepository.findById(it) }
        if (savedCart != null) {
            assertEquals(savedCart.get().member.id, member.id)
        }
    }
}