package com.ttd.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createAt;
	@LastModifiedDate
	private LocalDateTime updateAt;
	
//	public LocalDateTime getCreateAt() {
//		return createAt;
//	}
//	public LocalDateTime getUpdateAt() {
//		return updateAt;
//	}
	public String getCreateAt() {
		return createAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	public String getUpdateAt() {
		return updateAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
}
