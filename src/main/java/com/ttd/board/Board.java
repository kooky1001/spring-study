package com.ttd.board;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ttd.user.User;

@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long boardId;
	@JoinColumn
	@ManyToOne
	private User user;
	private String title;
	private String content;
	
	public Board() {
	}
	public Board(User user, String title, String content) {
		super();
		this.user = user;
		this.title = title;
		this.content = content;
	}
}
