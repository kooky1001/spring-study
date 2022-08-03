package com.ttd.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttd.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);
}
