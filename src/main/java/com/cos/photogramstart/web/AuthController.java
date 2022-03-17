package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor//3. final걸려있는 애들 생성자 만듦
@Controller//Ioc등록 파일리턴
public class AuthController {
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	//1.@Autowired
	private final AuthService authService;
	
	//2. 의존성 주입
//	public AuthController(AuthService authService) {
//		this.authService=authService;
//	}
	
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
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {//key=value(x-www-form-urlencoded)
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap= new HashMap<>();
			for(FieldError error:bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("=================");
				System.out.println(error.getDefaultMessage());
				System.out.println("=================");
			}
			throw new CustomValidationException("유효성검사 실패",errorMap);
		}else {
			//User<-SignupDto
			User user = signupDto.toEntity();
			User userEntity =authService.회원가입(user);
			System.out.println(userEntity);
			return "auth/signin";
		}
	
	}
}
