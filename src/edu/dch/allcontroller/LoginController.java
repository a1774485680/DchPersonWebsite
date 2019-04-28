package edu.dch.allcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.dch.bean.Userlogin;
import edu.dch.services.ILoginService;
import edu.dch.services.IPassageServices;
import edu.dch.services.LoginServiceImp;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/LoginController")
public class LoginController {
	public ILoginService loginService;
	
	public LoginController() {
		super();
		ApplicationContext app=new ClassPathXmlApplicationContext("resource/spring-All.xml");
		loginService=(LoginServiceImp)app.getBean("LoginServiceImp");
		
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/login.do")
	public void login(HttpServletResponse response,HttpServletRequest res) throws IOException{
		String name = res.getParameter("username");
		String password = res.getParameter("userpassword");
		Userlogin user=new Userlogin(name,password);
		int vail = loginService.VaildateByUserService(user);
		Map<String,Object> map=new HashMap<String,Object>();
		if(vail==1){
			System.out.println("³É¹¦");
			HttpSession session=res.getSession();
			session.setAttribute("user", user);	
			map.put("vail", vail);
		}else if(vail==0){
			map.put("vail", vail);
		}
		JSONObject myJson=JSONObject.fromObject(map);
		String json=myJson.toString();
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out =response.getWriter();
		out.print(json);
		out.close();
		System.out.println(json);
	}
}
