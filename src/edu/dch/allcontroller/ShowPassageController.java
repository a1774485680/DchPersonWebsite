package edu.dch.allcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.dch.services.IIssueServices;
import edu.dch.services.IShowPassageServices;

@Controller
@RequestMapping("/ShowPassage")
public class ShowPassageController {
	IShowPassageServices IShowService;
	public ShowPassageController(){
		ApplicationContext app=new ClassPathXmlApplicationContext("resource/spring-All.xml");
		IShowService=(IShowPassageServices)app.getBean("IShowPasServiceImp");
	}
	@RequestMapping("/passageLoad.do")
	public void passageLoad(HttpServletResponse response,HttpServletRequest res) throws IOException{
		String name = res.getParameter("name");
		String title = res.getParameter("title");
		String passTxt=IShowService.SelectByNameAndTitle(name, title);
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out =response.getWriter();
		out.print(passTxt);
		out.close();
	}
}
