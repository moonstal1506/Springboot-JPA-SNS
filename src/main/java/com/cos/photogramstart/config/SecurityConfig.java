package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.photogramstart.config.oauth.OAuth2DetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity//이 파일로 시큐리티를 활성화
@Configuration//Ioc  
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final OAuth2DetailsService oAuth2DetailsService;
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super삭제-기존 시큐리티가 가지고 있는 기능이 다 비활성화됨
		http.csrf().disable();//접근 구분 안함
		http.authorizeRequests()
			.antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**","/api/**").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/auth/signin")//인증이 안되어 있을 때 GET
			.loginProcessingUrl("/auth/signin")//스프링 시큐리티가 로그인 프로세스 진행 POST
			.defaultSuccessUrl("/")
			.and()
			.oauth2Login() //form로그인도 하는데, oauth2Login도 할거임
			.userInfoEndpoint() //최종응답을 회원정보로 받는다.
			.userService(oAuth2DetailsService);
	}
}
