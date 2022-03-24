package edu.web.service;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHConnection {
	private String username = DBConfig.username;
	private String host = DBConfig.host;
	private int port = 22;
	private String path = DBConfig.path;
	
	private Session session;
	
	public void connect() {
		try {
			JSch jsch = new JSch();
			session = jsch.getSession(username, host, port);
			jsch.addIdentity(path);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			session.setPortForwardingL(3306, "127.0.0.1", 3306);
			System.out.println("ssh connected!");
		} catch (JSchException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		session.disconnect();
		System.out.println("ssh disconnected!");
	}
}
