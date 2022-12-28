package com.shop.springbootkotlinjpashop.config.auth

import com.shop.springbootkotlinjpashop.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class PrincipalDetails(private val member: Member) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
       val authorities: Collection<GrantedAuthority> = ArrayList()
        authorities.plus(member.role)
       return authorities
    }

    override fun getPassword(): String {
        return member.password
    }

    override fun getUsername(): String {
        return member.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}