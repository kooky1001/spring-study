package edu.web.service.notice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.web.entity.notice.Notice;
import edu.web.service.SSHConnection;

public class NoticeService {
	
	private String url = "jdbc:mysql://127.0.0.1:3306/edu";
	private String user = "";
	private String pwd = "";
	
	public List<Notice> getList() throws SQLException {
		SSHConnection ssh = new SSHConnection();
		ssh.connect();
		String sql = "select * from notice"; 
		// Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, user, pwd);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<Notice> list = new ArrayList<Notice>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String writerId = rs.getString("writer_id");
			Date regDate = rs.getDate("regdate");
			String content = rs.getString("content");
			int hit = rs.getInt("hit");
			String files = rs.getString("files");
			list.add(new Notice(id, title, writerId, regDate, content, hit, files));
		}
		rs.close();
		stmt.close();
		conn.close();
		ssh.disconnect();
		return list;
	}
}
