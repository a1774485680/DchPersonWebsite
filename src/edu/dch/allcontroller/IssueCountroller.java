package edu.dch.allcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.dch.bean.Userlogin;
import edu.dch.services.IIssueServices;
import edu.dch.services.IShowPasServiceImp;
import edu.dch.services.IShowPassageServices;
import edu.dch.services.IssueServiceImp;
import edu.dch.utils.ParamsUtil;


@Controller
@RequestMapping("/Issue")
public class IssueCountroller {
	IIssueServices IssueService;
	IShowPassageServices IShowService;
	public IssueCountroller(){
		ApplicationContext app=new ClassPathXmlApplicationContext("resource/spring-All.xml");
		IssueService=(IIssueServices)app.getBean("IssueServiceImp");
		IShowService=(IShowPassageServices)app.getBean("IShowPasServiceImp");
//		IssueService=new IssueServiceImp();
//		IShowService=new IShowPasServiceImp();
	}
	@RequestMapping("/AllpageByIssue.do")
	public void AllpageByIssue(HttpServletResponse response,HttpServletRequest res) throws IOException{
		HttpSession session=res.getSession();
		Userlogin user= (Userlogin)session.getAttribute("user");
		String allpage="";
		if(user==null){
			allpage =IssueService.Allpage(null);
		}else{
			
			 allpage = IssueService.Allpage(user.getUsername());
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter out =response.getWriter();
		out.print(allpage);
		out.close();
	}
	@RequestMapping("/passageLoad.do")
	public void passageLoad(HttpServletResponse response,HttpServletRequest res) throws IOException{
		//调用方法获得json格式的数据（从数据库来）
		HttpSession session=res.getSession();
		Userlogin user= (Userlogin)session.getAttribute("user");
		String StringJson=IssueService.PassageLoad(user.getUsername());
		//设置字符编码
		response.setCharacterEncoding("utf-8");
		System.out.println(StringJson);
		PrintWriter out =response.getWriter();
		out.print(StringJson);
		out.close();
	}
	@RequestMapping("/goPage.do")
	public void goPage(HttpServletResponse response,HttpServletRequest res) throws IOException{
		String page = res.getParameter("goPage");
		HttpSession session=res.getSession();
		Userlogin user= (Userlogin)session.getAttribute("user");
		int goPage=Integer.valueOf(page);
		String goPageJson = IssueService.goPageByIssue( user.getUsername(),goPage);//调用
		response.setCharacterEncoding("utf-8");
		PrintWriter out =response.getWriter();
		out.print(goPageJson);
		out.close();
	}
	@RequestMapping("/downPage.do")
	public void downPage(HttpServletResponse response,HttpServletRequest res) throws IOException{
		//调用方法获得json格式的数据（从数据库来）
		String page = res.getParameter("downPage");
		HttpSession session=res.getSession();
		Userlogin user= (Userlogin)session.getAttribute("user");		
		int downPage=Integer.valueOf(page);
		String downPageJson = IssueService.goPageByIssue(user.getUsername(),downPage);
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out =response.getWriter();
		out.print(downPageJson);
		out.close();
	}@RequestMapping("/upPage.do")
	public void upPage(HttpServletResponse response,HttpServletRequest res) throws IOException{
		//获得前端来的数
		String page = res.getParameter("upPage");
		HttpSession session=res.getSession();
		Userlogin user= (Userlogin)session.getAttribute("user");
		int upPage=Integer.valueOf(page);
		String upPageJson = IssueService.goPageByIssue(user.getUsername(), upPage);
		response.setCharacterEncoding("utf-8");
	
		PrintWriter out =response.getWriter();
		out.print(upPageJson);
		out.close();
	}
	@RequestMapping("/publish.do")
	public void publish1(HttpServletResponse response,HttpServletRequest res) throws IOException{		
		String param = ParamsUtil.getStreamToString(res); // 接收流参数并转成字符串
		Map<String, String> params = ParamsUtil.getUrlParams(param); 
		Set<String> keys =params.keySet();   //此行可省略，直接将map.keySet()写在for-each循环的条件中
		
		String Pclassify = params.get("Pclassify");
		String pbrief = params.get("pbrief");
		String Ptitle = params.get("Ptitle");
		HttpSession session=res.getSession();
		res.getInputStream();
		Userlogin user= (Userlogin)session.getAttribute("user");
		String html = params.get("textHtml");
		String fal = IssueService.writePassage(user.getUsername(), Ptitle, Pclassify, html, pbrief);	
		response.setCharacterEncoding("utf-8");
		PrintWriter out =response.getWriter();
		out.print(fal);
		out.close();
	}
	@RequestMapping("/updataPassage.do")
	public void updataPassage(HttpServletResponse response,HttpServletRequest res) throws IOException{		
		String title=res.getParameter("title");
		HttpSession session=res.getSession();
		Userlogin user=(Userlogin)session.getAttribute("user");
		String passTxt=IShowService.SelectByNameAndTitle(user.getUsername(), title);
		response.setCharacterEncoding("utf-8");		
		PrintWriter out =response.getWriter();
		out.print(passTxt);
		out.close();
	}
	@RequestMapping("/updatepublish.do")
	public void updatepublish(HttpServletResponse response,HttpServletRequest res) throws IOException{		
		String param = ParamsUtil.getStreamToString(res); // 接收流参数并转成字符串
		Map<String, String> params = ParamsUtil.getUrlParams(param); 
		Set<String> keys =params.keySet();   //此行可省略，直接将map.keySet()写在for-each循环的条件中
		
		String Pclassify = params.get("Pclassify");
		String pbrief = params.get("pbrief");
		String Ptitle = params.get("Ptitle");
		HttpSession session=res.getSession();
		res.getInputStream();
		Userlogin user= (Userlogin)session.getAttribute("user");
		String html = params.get("textHtml");
		String fal = IssueService.updatewritePassage(user.getUsername(), Ptitle, Pclassify, html, pbrief);	
		response.setCharacterEncoding("utf-8");
		PrintWriter out =response.getWriter();
		System.out.println("fal     "+fal);
		out.print(fal);
		out.close();
	}
	@RequestMapping("/getinformPassage.do")
	public void getinformPassage(HttpServletResponse response,HttpServletRequest res) throws IOException{		
		String title=res.getParameter("title");
		HttpSession session=res.getSession();
		Userlogin user=(Userlogin)session.getAttribute("user");
		String inform = IShowService.SelectByNameAndTitleOfpassageinfrom(user.getUsername(), title);
		response.setCharacterEncoding("utf-8");
		PrintWriter out =response.getWriter();
		out.print(inform);
		out.close();
	}
}
