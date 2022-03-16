package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller//Ioc등록 파일리턴
public class AuthController {
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	
	//회원가입 버튼 -> /auth/signup-> /auth/signin
	//안되는 이유 csrf토큰
	@PostMapping("/auth/signup")
	public String signup() {
		System.out.println("signup실행");
		return "auth/signin";
	}
}
