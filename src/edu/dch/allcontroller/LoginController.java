package edu.dch.allcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.dch.bean.Passage;
import edu.dch.bean.Userlogin;
import edu.dch.services.ILoginService;
import edu.dch.services.IPassageServices;
import edu.dch.services.LoginServiceImp;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/LoginController")
public class LoginController {
	@Autowired
	@Qualifier("LoginServiceImp")
	public ILoginService loginService;


	@RequestMapping("/login.do")
	public void login(HttpServletResponse response,HttpServletRequest res) throws IOException{
		String name = res.getParameter("username");
		String password = res.getParameter("userpassword");
		Userlogin user=new Userlogin(name,password);
		int vail = loginService.VaildateByUserService(user);
		System.out.println(vail);
		Map<String,Object> map=new HashMap<String,Object>();
		if(vail==1){
			System.out.println("成功");
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
		
	}
	@RequestMapping("/loginout.do")
	public void loginout(HttpServletResponse response,HttpServletRequest res) throws IOException{
		HttpSession session=res.getSession();
		session.removeAttribute("user");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out =response.getWriter();
		out.print("1");
		out.close();
	}
	@RequestMapping("/checklogin.do")//检查是否登陆
	public void checklogin(HttpServletResponse response,HttpServletRequest res) throws IOException{
		HttpSession session=res.getSession();
		Userlogin user= (Userlogin)session.getAttribute("user");
		List<Userlogin> user1= new ArrayList<Userlogin>();
		if(user==null){
			user1.add(new Userlogin("未登陆","未登陆"));
		}else{
			user1.add(user);
		}
		//创建一个json对象
		ObjectMapper mapper=new ObjectMapper();
		String a="";//json返回的数组
		try {
			a = mapper.writeValueAsString(user1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");		
		PrintWriter out =response.getWriter();
		out.print(a);
		out.close();
	}
}
