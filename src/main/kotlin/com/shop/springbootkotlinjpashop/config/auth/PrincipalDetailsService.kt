package com.shop.springbootkotlinjpashop.config.auth

import com.shop.springbootkotlinjpashop.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class PrincipalDetailsService (
    @Autowired private val memberRepository: MemberRepository
): UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val member = memberRepository.findByEmail(email) ?: throw UsernameNotFoundException(email)

        return PrincipalDetails(member)

    }


}