package edu.dch.allcontroller;


import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloPageController {
	@RequestMapping("/hello.do")
	public void doSome(String name,String age,HttpServletRequest res){
		System.out.println("ves");
		System.out.println("name ="+name);
		System.out.println("age ="+age);
		System.out.println(res.getContextPath());
	}
	@Test
	public void testone(){
		
	}
}
