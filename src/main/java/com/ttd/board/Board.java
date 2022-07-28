package com.ttd.board;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ttd.reply.Reply;
import com.ttd.user.User;
import com.ttd.web.BaseEntity;

@Entity
public class Board extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long boardId;
	@JoinColumn
	@ManyToOne
	private User user;
	private String title;
	@Lob
	private String content;
	@OneToMany(mappedBy = "board", orphanRemoval = true)
	private List<Reply> replies;
	
	public Board() {
	}
	public Board(String title, String content) {
		this.title = title;
		this.content = content;
	}
	public Board(User user, String title, String content) {
		this.user = user;
		this.title = title;
		this.content = content;
	}
	
	public long getBoardId() {
		return boardId;
	}
	public User getUser() {
		return user;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	
	public void update(Board updateBoard) {
		this.title = updateBoard.title;
		this.content = updateBoard.content;
	}
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", user=" + user + ", title=" + title + ", content=" + content + "]";
	}
	
	public boolean isSameUser(User loginUser) {
		return this.user.equals(loginUser);
	}
}
