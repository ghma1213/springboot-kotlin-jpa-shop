package com.shop.springbootkotlinjpashop.config

import com.shop.springbootkotlinjpashop.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig(
    @Autowired
    private var memberService: MemberService,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.csrf().disable()
            .formLogin().loginPage("/members/login")
            .defaultSuccessUrl("/").usernameParameter("email")
            .failureUrl("/members/login/error")
            .and()
            .logout()
            .logoutRequestMatcher(AntPathRequestMatcher("/members/logout"))
            .logoutSuccessUrl("/")
            .and()
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

//    private fun configure(auth: AuthenticationManagerBuilder) {
//        auth.userDetailsService(memberService)
//            .passwordEncoder(passwordEncoder())
//    }
}