package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@Column(length=20, unique=true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	private String website;//웹사이트
	private String bio;//자기소개
	@Column(nullable = false)
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl;//사진
	private String role;//권한
	
	//mappedBy 나는 연관관계 주인이 아니다. 테이블에 칼럼 만들지마
	//FetchType.LAZY:User를 select할 때 해당 User id로 등록된 image들을 가져오지마-getImages()의 이미지들이 호출될 때 가져와
	//FetchType.EAGER:User를 select할 때 해당 User id로 등록된 image들을 가져와
	@OneToMany(mappedBy="user", fetch =FetchType.LAZY)
	@JsonIgnoreProperties({"user"})//Image내부에 있는 user는 하지마
	private List<Image> images;//양방향 매핑
	
	private LocalDateTime createDate;
	
	@PrePersist//db에 insert 되기 직전에 실행
	public void createDate() {
		this.createDate=LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", website="
				+ website + ", bio=" + bio + ", email=" + email + ", phone=" + phone + ", gender=" + gender
				+ ", profileImageUrl=" + profileImageUrl + ", role=" + role +", createDate="
				+ createDate + "]";
	}
	
	
}
