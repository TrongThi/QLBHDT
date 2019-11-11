package spring.qlbh.QLBHDT.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.qlbh.QLBHDT.dao.UserDAO;
import spring.qlbh.QLBHDT.model.UserInfo;

@Controller
public class HomeController {

	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value = "/")
	public String index(HttpServletRequest request) {
		List<UserInfo> list = userDAO.getList();
		
		request.setAttribute("ListUser", list);
		
		return "index";
	}
}
