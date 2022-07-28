package com.ttd.reply;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.ttd.board.Board;
import com.ttd.user.User;
import com.ttd.web.BaseEntity;

@Entity
public class Reply extends BaseEntity {
	@ManyToOne
	private User user;
	@ManyToOne
	private Board board;
	@Lob
	private String content;
	@Override
	public String toString() {
		return "Reply [replyId=" + super.getId() + ", user=" + user + ", board=" + board + ", content=" + content + "]";
	}
	public Reply() {
	}
	public Reply(User user, Board board, String content) {
		this.user = user;
		this.board = board;
		this.content = content;
	}
	
	public User getUser() {
		return user;
	}
	public Board getBoard() {
		return board;
	}
	public String getContent() {
		return content;
	}
	
	public boolean isSameUser(User loginUser) {
		return loginUser.equals(this.user);
	}
}
