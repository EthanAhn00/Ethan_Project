package com.grepp.spring.app.controller.web.member;

import com.grepp.spring.app.controller.web.member.form.SigninForm;
import com.grepp.spring.app.controller.web.member.form.SignupForm;
import com.grepp.spring.app.model.member.MemberService;
import com.grepp.spring.app.model.member.code.Role;
import com.grepp.spring.app.model.member.dto.Member;
import com.grepp.spring.app.model.member.dto.Principal;
import com.grepp.spring.infra.error.exceptions.CommonException;
import com.grepp.spring.infra.response.ResponseCode;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("member")
public class MemberController {
    
    private final MemberService memberService;

    @GetMapping("signup")
    public String signup(SignupForm form){
        return "member/signup";
    }
    
    @PostMapping("signup")
    public String signup(
        @Valid SignupForm form,
        BindingResult bindingResult,
        Model model){
        
        if(bindingResult.hasErrors()){
            return "member/signup";
        }
        
        memberService.signup(form.toDto());
        return "redirect:/";
    }
    
    @GetMapping("signin")
    public String signin(SigninForm form){
        return "member/signin";
    }
    
    @PostMapping("signin")
    public String signin(
        @Valid SigninForm form,
        BindingResult bindingResult,
        HttpSession session,
        RedirectAttributes redirectAttributes){
        
        if(bindingResult.hasErrors()){
            return "member/signin";
        }
        
        Principal principal = memberService.signin(form.getUserId(), form.getPassword());
        
        if(principal.equals(Principal.ANONYMOUS)){
            redirectAttributes.addAttribute("error", null);
            return "redirect:/member/signin";
        }
        
        session.setAttribute("principal", principal);
        return "redirect:/";
    }
    
    // NOTE cors
    // cross origin resource sharing
    // origin : protocol + host + port 
    // allowCredentials : 쿠키, 인증 헤더 허용
    // allowMethods : cors 를 허용할 메소드 지정
    // allowHeaders : cors 를 허용할 http 헤더 지정
    //@CrossOrigin(origins = "http://localhost:63342", allowCredentials = "true")
    @GetMapping("info")
    public String allInfo(
        @SessionAttribute(name = "principal", required = false)
        Principal principal,
        RedirectAttributes redirectAttributes){
        
        if(principal == null || principal.Roles().contains(Role.ANONYMOUS))
            throw new CommonException(ResponseCode.UNAUTHORIZED);
        
        log.info("당신의 모든 개인정보를 탈취당했습니다.");
        redirectAttributes.addAttribute("error", "당신의 모든 개인정보입니다.");
        return "redirect:/";
    }
}
