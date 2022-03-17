package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//JPA-Java persistence API 자바 데이터를 영구적으로 저장(db)할 수 있는 API를 제공

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity//db에 테이블 생성
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//번호 증가 전략 데이터베이스 따라감
	private int id;
	
	@Column(unique=true)
	private String username;
	private String password;
	
	private String name;
	private String website;//웹사이트
	private String bio;//자기소개
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl;//사진
	private String role;//권한
	
	private LocalDateTime createDate;
	
	@PrePersist//db에 insert 되기 직전에 실행
	public void createDate() {
		this.createDate=LocalDateTime.now();
	}
	
}
