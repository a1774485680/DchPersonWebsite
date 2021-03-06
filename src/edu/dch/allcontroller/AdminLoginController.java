package edu.dch.allcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.dch.services.IAdminService;
import edu.dch.services.IShowPassageServices;


@Controller
@RequestMapping("/AdminLoginControlle")
public class AdminLoginController {
	@Autowired
	@Qualifier("AdminServiceImp")
	public IAdminService adminService;
	@RequestMapping("/AdminLogin")
	public void adminLogin(HttpServletResponse response,HttpServletRequest res) throws IOException{
		String name = res.getParameter("username");
		String password = res.getParameter("userpassword");
		int i=adminService.verifyAdmin(name,password);
		response.setCharacterEncoding("utf-8");		
		PrintWriter out =response.getWriter();
		out.print(i);
		out.close();
	}
}
