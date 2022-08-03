package com.ttd.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends BaseEntity {
	
	@Column(nullable = false, length = 20, unique = true)
	private String userId;
	
	private String name;
	private String password;
	private String email;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String toString() {
		return "Member [id=" + super.getId() + ", userId=" + userId + ", name=" + name + ", password=" + password + ", email="
				+ email + "]";
	}
	
	public void update(User updateUser) {
		this.name = updateUser.name;
		this.password = updateUser.password;
		this.email = updateUser.email;
	}
	
	public boolean matchPassword(String password) {
		return password.equals(this.password);
	}
	public boolean matchId(long id) {
		return id == super.getId();
	}
	
	
}
