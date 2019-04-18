package edu.dch.allcontroller;



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
}
