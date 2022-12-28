package com.shop.springbootkotlinjpashop.service

import com.shop.springbootkotlinjpashop.entity.Member
import com.shop.springbootkotlinjpashop.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    @Autowired
    private var memberRepository: MemberRepository
): UserDetailsService  {
    fun saveMember(member: Member): Member {
        validateDuplicateMember(member)
        return memberRepository.save(member)
    }

    fun validateDuplicateMember(member: Member) {
        val findMember = memberRepository.findByEmail(member.email)
        if (findMember != null) {
            throw IllegalStateException("이미 가입된 회원입니다.")
        }

    }

    override fun loadUserByUsername(email: String): UserDetails {
        val member = memberRepository.findByEmail(email) ?: throw UsernameNotFoundException(email)

        return User.builder()
            .username(member.email)
            .password(member.password)
            .roles(member.role.toString())
            .build()

    }
}