package edu.web.service.notice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.web.entity.notice.Notice;
import edu.web.service.DBConfig;
import edu.web.service.SSHConnection;

@Service
public class NoticeService {
	
	private String url = DBConfig.url;
	private String user = DBConfig.user;
	private String pwd = DBConfig.pwd;
	private String driver = DBConfig.driver;
	
	public List<Notice> getList() throws SQLException, ClassNotFoundException {
		SSHConnection ssh = new SSHConnection();
		ssh.connect();
		String sql = "select * from notice"; 
		Class.forName(driver);
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
	
	public Notice getDetail(String id) throws ClassNotFoundException, SQLException {
		SSHConnection ssh = new SSHConnection();
		ssh.connect();
		String sql = "select * from notice where id=?"; 
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, pwd);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();
		Notice notice = null;
		while (rs.next()) {
			int nid = rs.getInt("id");
			String title = rs.getString("title");
			String writerId = rs.getString("writer_id");
			Date regDate = rs.getDate("regdate");
			String content = rs.getString("content");
			int hit = rs.getInt("hit");
			String files = rs.getString("files");
			notice = new Notice(nid, title, writerId, regDate, content, hit, files);
		}
		rs.close();
		stmt.close();
		conn.close();
		ssh.disconnect();
		
		return notice;
	}
}
