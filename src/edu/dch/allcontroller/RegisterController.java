package edu.dch.allcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.dch.bean.Userlogin;
import edu.dch.services.IRegisterService;
import edu.dch.services.RegisterServiceImp;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/Register")
public class RegisterController {
	IRegisterService registetServices;
	public RegisterController(){
		registetServices=new RegisterServiceImp();
	}
	@RequestMapping("/UsernameVerify.do")
	public void usernameVerify(String username,HttpServletResponse response ) throws IOException{
		
		String username1=username;
		HashMap<String,Object> map = new HashMap<String,Object>();
		if(registetServices.usernameVerify(username1)){
			map.put("userExsit", true);
			map.put("msg", "�û����Ѿ�����");
		}else{
			map.put("userExsit", false);
			map.put("msg", "�û�������");
		}
		JSONObject myJsom=JSONObject.fromObject(map);
		String jsonString=myJsom.toString();
		response.setCharacterEncoding("utf-8");
		PrintWriter out =response.getWriter();
		out.print(jsonString);
		out.close();
	}
	@RequestMapping("/UsernameRegister.do")
	public void userRegister(String name,String password){
		System.out.println(name+"  "+password);
		registetServices.userRegiser(new Userlogin(name,password));
	}
}
