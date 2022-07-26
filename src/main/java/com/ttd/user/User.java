package com.ttd.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ttd.web.BaseEntity;

@Entity
public class User extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 20, unique = true)
	private String userId;
	
	private String name;
	private String password;
	private String email;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", userId=" + userId + ", name=" + name + ", password=" + password + ", email="
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
		return id == this.id;
	}
}
