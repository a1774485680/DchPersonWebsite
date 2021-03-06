package edu.dch.allcontroller;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.dch.services.IPassageServices;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/Passage")
public class PassageController {
	@Autowired
	@Qualifier("PassageLoadImp")
	IPassageServices passageService;
	@RequestMapping("/passageLoad.do")
	public void passageLoad(HttpServletResponse response) throws IOException{
		//调用方法获得json格式的数据（从数据库来）
		String StringJson=passageService.PassageLoad(1);
		//设置字符编码
		response.setCharacterEncoding("utf-8");
		System.out.println("cs"+StringJson);
		PrintWriter out =response.getWriter();
		out.print(StringJson);
		out.close();
	}
	@RequestMapping("/allPage.do")
	public void allPage(HttpServletResponse response,HttpServletRequest res) throws IOException{
		//调用方法获得json格式的数据（从数据库来）
		String NowCategory = res.getParameter("NowCategory");
		System.out.println("NowCategory         by allPage.do    "+NowCategory);
		int allCount = passageService.allCountPassage(NowCategory );
		//设置字符编码
		
		HashMap<String,Object> map = new HashMap<String,Object>();//定义map
		map.put("count", allCount);
		response.setCharacterEncoding("utf-8");
		JSONObject myJsom=JSONObject.fromObject(map);
		String jsonString=myJsom.toString();
		PrintWriter out =response.getWriter();
		out.print(jsonString);
		out.close();
	}
	@RequestMapping("/goPage.do")
	public void goPage(HttpServletResponse response,HttpServletRequest res) throws IOException{
		String page = res.getParameter("goPage");
		String NowCategory = res.getParameter("NowCategory");
		int goPage=Integer.valueOf(page);
		String goPageJson = passageService.classifyPassage(NowCategory, goPage);//调用
		response.setCharacterEncoding("utf-8");
		System.out.println("cs"+goPageJson);
		PrintWriter out =response.getWriter();
		out.print(goPageJson);
		System.out.println(goPageJson);
		out.close();
	}@RequestMapping("/downPage.do")
	public void downPage(HttpServletResponse response,HttpServletRequest res) throws IOException{
		//调用方法获得json格式的数据（从数据库来）
		String page = res.getParameter("downPage");
		String NowCategory = res.getParameter("NowCategory");
		System.out.println("now  ="+NowCategory);
		int downPage=Integer.valueOf(page);
		String downPageJson = passageService.classifyPassage(NowCategory,downPage);
		response.setCharacterEncoding("utf-8");
		System.out.println("cs"+downPageJson);
		PrintWriter out =response.getWriter();
		out.print(downPageJson);
		out.close();
	}@RequestMapping("/upPage.do")
	public void upPage(HttpServletResponse response,HttpServletRequest res) throws IOException{
		//获得前端来的数
		String page = res.getParameter("upPage");
		String NowCategory = res.getParameter("NowCategory");
		int upPage=Integer.valueOf(page);
		String upPageJson = passageService.classifyPassage(NowCategory, upPage);
		response.setCharacterEncoding("utf-8");
		System.out.println("cs"+upPageJson);
		PrintWriter out =response.getWriter();
		out.print(upPageJson);
		out.close();
	}
	@RequestMapping("/classify.do")
	public void classify(HttpServletResponse response,HttpServletRequest res) throws IOException{
		
		String NowCategory = res.getParameter("NowCategory");
		System.out.println("NowCategory        by classify.do    "+NowCategory);
		String upPageJson = passageService.classifyPassage(NowCategory, 1);
		response.setCharacterEncoding("utf-8");		
		PrintWriter out =response.getWriter();
		out.print(upPageJson);
		out.close();
	}
}
