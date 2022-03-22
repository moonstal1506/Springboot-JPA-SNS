package com.cos.photogramstart.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{

	private static final long serialVersionUID = 1L;
	
	private User user;
	private Map<String, Object> attributes;
	
	public PrincipalDetails(User user) {
		this.user =user;
	}
	
	public PrincipalDetails(User user,Map<String, Object> attributes) {
		this.user =user;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//권한 컬렉션타입 한개가 아닐 수 있음
		
		Collection<GrantedAuthority> collector= new ArrayList<>();
		collector.add(()-> {
				return user.getRole();
		});
		return collector;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { //계정 만료되지 않음
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {// 계정 안잠김
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //계정유효기간
		return true;
	}

	@Override
	public boolean isEnabled() {//계정활성화
		return true;
	}


	@Override
	public Map<String, Object> getAttributes() {
		return attributes;//{id=10340590405, name=문수정, email=moonstal@gmail.com}
	}


	@Override
	public String getName() {
		return (String) attributes.get("name");
	}

}
