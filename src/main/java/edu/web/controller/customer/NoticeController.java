package edu.web.controller.customer;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.web.entity.notice.Notice;
import edu.web.service.notice.NoticeService;

@Controller
@RequestMapping("/customer/notice/")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("list")
	public ModelAndView list() throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		List<Notice> list = noticeService.getList();
		mv.addObject("list", list);
		mv.setViewName("notice.list");
		return mv;
	}
	
	@RequestMapping("detail")
	public ModelAndView detail(String id) throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		Notice notice = noticeService.getDetail(id);
		mv.addObject("notice", notice);
		mv.setViewName("notice.detail");
		return mv;
	}
}
