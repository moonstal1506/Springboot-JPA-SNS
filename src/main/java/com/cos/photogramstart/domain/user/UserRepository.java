package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션이 없어도 JpaRepository상속하면 Ioc등록이 자동으로 됨
public interface UserRepository extends JpaRepository<User, Integer>{
	//JPA query method
	User findByUsername(String username);
}
