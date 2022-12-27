package com.shop.springbootkotlinjpashop.service

import com.shop.springbootkotlinjpashop.dto.MemberFormDto
import com.shop.springbootkotlinjpashop.entity.Member
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.TestPropertySource
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalStateException

@SpringBootTest
@Transactional
@TestPropertySource(locations = ["classpath:application-test.yml"])
class MemberServiceTest {
    @Autowired
    private lateinit var memberService: MemberService

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun createMember(): Member {
        val memberFormDto = MemberFormDto(
            email = "test@email.com",
            name = "홍길동",
            address = "서울시 마포구 합정동",
            password = "1234"
        )
        return Member.createMember(memberFormDto, passwordEncoder)
    }

    @Test
    @DisplayName("회원가입 테스트")
    fun saveMemberTest() {
        val member = createMember()
        val savedMember = memberService.saveMember(member)

        assertEquals(member.email, savedMember.email)
    }

    @Test
    @DisplayName("중복 회원가입 테스트")
    fun saveDuplicateMemberTest() {
        val member1 = createMember()
        val member2 = createMember()
        memberService.saveMember(member1)

        val e = assertThrows(IllegalStateException::class.java) {
            memberService.saveMember(member2)
        }

        assertEquals("이미 가입된 회원입니다.", e.message)

    }
}