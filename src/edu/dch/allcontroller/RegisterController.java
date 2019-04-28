package edu.dch.allcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.dch.bean.Userlogin;
import edu.dch.services.IRegisterService;
import edu.dch.services.RegisterServiceImp;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/Register")
public class RegisterController {
	IRegisterService registetServices;
	public RegisterController(){
		ApplicationContext app=new ClassPathXmlApplicationContext("resource/spring-All.xml");
		registetServices=(IRegisterService) app.getBean("RegisterServiceImp");
	}
	@RequestMapping("/UsernameVerify.do")
	public void usernameVerify(String username,HttpServletResponse response ) throws IOException{
		
		String username1=username;
		HashMap<String,Object> map = new HashMap<String,Object>();
		if(registetServices.usernameVerify(username1)){
			map.put("userExsit", true);
			map.put("msg", "用户名已经存在");
		}else{
			map.put("userExsit", false);
			map.put("msg", "用户名可用");
		}
		JSONObject myJsom=JSONObject.fromObject(map);
		String jsonString=myJsom.toString();
		System.out.println(jsonString);
		response.setCharacterEncoding("utf-8");
		PrintWriter out =response.getWriter();
		out.print(jsonString);
		out.close();
	}
	@RequestMapping("/UsernameRegister.do")
	public ModelAndView userRegister(String name,String password){
		System.out.println(name+"  "+password);
		registetServices.userRegiser(new Userlogin(name,password));
		ModelAndView mv=new ModelAndView();
		mv.setViewName("redirect:/ALLHTML/NewFile.html");
		return mv;
	}
}
