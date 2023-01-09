package com.shop.springbootkotlinjpashop.entity

import com.shop.springbootkotlinjpashop.constant.Role
import com.shop.springbootkotlinjpashop.dto.MemberFormDto
import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder

@Entity
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    val id: Long? = null,

    val name: String,

    @Column(unique = true)
    val email: String,

    val password: String,

    val address: String,

    @Enumerated(EnumType.STRING)
    val role: Role,

) {


    companion object {
        fun createMember(memberFormDto: MemberFormDto, passwordEncoder: PasswordEncoder) = Member(
            name = memberFormDto.name,
            email = memberFormDto.email,
            address = memberFormDto.address,
            password = passwordEncoder.encode(memberFormDto.password),
            role = Role.USER
        )
    }
}