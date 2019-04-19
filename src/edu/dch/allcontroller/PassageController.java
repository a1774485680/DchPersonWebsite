package edu.dch.allcontroller;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.dch.services.IPassageServices;

@Controller
@RequestMapping("/Passage")
public class PassageController {
	IPassageServices passageService;
	public PassageController(){
		ApplicationContext app=new ClassPathXmlApplicationContext("resource/spring-All.xml");
		passageService=(IPassageServices)app.getBean("PassageLoadImp");
	}
	@RequestMapping("/passageLoad.do")
	public void passageLoad(HttpServletResponse response) throws IOException{
		//调用方法获得json格式的数据（从数据库来）
		String StringJson=passageService.PassageLoad();
		//设置字符编码
		response.setCharacterEncoding("utf-8");
		System.out.println("cs"+StringJson);
		PrintWriter out =response.getWriter();
		out.print(StringJson);
		out.close();
	}
}
