package com.ttd.user;

import java.util.Objects;

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
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id;
	}
	
}
