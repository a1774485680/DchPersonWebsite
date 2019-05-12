package edu.dch.allcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.dch.services.IAdminService;
import edu.dch.services.IPassageServices;
import edu.dch.services.IPassageServices2;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	@Qualifier("PassageLoadImp2")
	IPassageServices2 passageService;
	
	public void setPassageService(IPassageServices2 passageService) {
		this.passageService = passageService;
	}

	@RequestMapping("/test01.do")
	public void passageLoad(HttpServletResponse response) throws IOException{
		//调用方法获得json格式的数据（从数据库来）
		passageService.test();
	}
}
