package edu.web.service.notice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.web.entity.notice.Notice;
import edu.web.service.SSHConnection;

public class NoticeService {
	
	private String url = "jdbc:mysql://127.0.0.1:3306/edu";
	private String user = "";
	private String pwd = "";
	
	public void test() {
		SSHConnection ssh = new SSHConnection();
		ssh.connect();
		String sql = "select * from notice"; 
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pwd);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				System.out.println(id + title);
			}
			ssh.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
