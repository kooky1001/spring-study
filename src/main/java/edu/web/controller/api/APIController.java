package edu.web.controller.api;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.web.entity.notice.Notice;
import edu.web.service.notice.NoticeService;

@RestController
@RequestMapping("/api/")
public class APIController {

	@Autowired
	private NoticeService service;
	
	@RequestMapping("test")
	public String test() {
		return "test";
	}
	
	@RequestMapping("detail")
	public Notice detail() throws ClassNotFoundException, SQLException {
		Notice notice = service.getDetail("1");
		System.out.println(notice.toString());
		return notice;
	}
}
