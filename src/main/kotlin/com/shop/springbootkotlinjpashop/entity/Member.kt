package com.shop.springbootkotlinjpashop.entity

import com.shop.springbootkotlinjpashop.constant.Role
import com.shop.springbootkotlinjpashop.dto.MemberFormDto
import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder

@Entity
data class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id", nullable = false)
    var id: Long? = null,

    var name: String,

    @Column(unique = true)
    var email: String,

    var password: String,

    var address: String,

    @Enumerated(EnumType.STRING)
    var role: Role,

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