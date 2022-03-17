package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {
	private String name;//필수
	private String password;//필수
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	
	//필수값 아닌데 이렇게 작성하면 위험. 코드 수정 필요
	public User toEntity() {
		return User.builder()
				.name(name) //이름 공백 문제, validation 체크
				.password(password)//패스워드 공백 문제, validation 체크
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
	}
}
