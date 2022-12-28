package com.shop.springbootkotlinjpashop.controller

import com.shop.springbootkotlinjpashop.dto.MemberFormDto
import com.shop.springbootkotlinjpashop.entity.Member
import com.shop.springbootkotlinjpashop.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/members")
@Controller
class MemberController(
    @Autowired
    private var memberService: MemberService,
    private var passwordEncoder: PasswordEncoder
) {
    @GetMapping("/new")
    fun memberForm (model: Model): String {
        model.addAttribute("memberFormDto", MemberFormDto("", "", "", ""))
        return "member/memberForm"
    }

    @PostMapping("/new")
    fun memberForm(memberFormDto: MemberFormDto): String {
        println("memberFormDto = $memberFormDto")
        val member = Member.createMember(memberFormDto, passwordEncoder)
        memberService.saveMember(member)
        return "redirect:/"
    }

    @GetMapping("/login")
    fun loginMember(): String {
        return "/member/memberLoginForm"
    }

    @GetMapping("/login/error")
    fun loginError(model: Model): String {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.")
        return "/member/memberLoginForm"
    }
}