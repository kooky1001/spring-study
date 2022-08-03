package com.ttd.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttd.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);
}
