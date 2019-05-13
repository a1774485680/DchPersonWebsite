package edu.dch.services;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.dch.bean.Passage;
import edu.dch.bean.Userlogin;
import edu.dch.dao.IPassageDao;
import edu.dch.dao.IUserLoginDao;
import edu.dch.utils.MybatisSqlSessionutils;

import edu.dch.utils.PassageWriteUtil;


@Component("IssueServiceImp")
public class IssueServiceImp implements IIssueServices {
	@Resource(name="IPassageDao")
	public IPassageDao passagedao;
	
	private PassageWriteUtil passWrite;
	@Resource(name="IUserLoginDao")
	private IUserLoginDao LoginDao;
	public IssueServiceImp() {
		super();
		
		passWrite=new PassageWriteUtil();
	}
	
	//获得有多少页并且 把该页设置为json格式
	@Override
	public String Allpage(String name) {
		// TODO Auto-generated method stub
		//获得一共有多少文章
		int allCount = passagedao.CountAllpage(name);
		//将文章设置为页
		int Allpage=0;
		if(name==null){//如果没有登陆转到登陆界面
			Allpage=-1;
		}else{
			//转换
			int i=allCount/10;
			int j=allCount%10;
			if(j!=0){
				Allpage=i+1;
			}else{
				Allpage=i;
			}
		}
		//将得到的页转换为字符串
		HashMap<String,Object> map = new HashMap<String,Object>();
		ObjectMapper mapper=new ObjectMapper();
		map.put("count", Allpage);
		String s="";
		try {
			s=mapper.writeValueAsString(map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return s;
	}

	@Override
	public String PassageLoad(String username) {

		
		List<Passage> StringList= passagedao.SelectByUserName(username);

		for (Passage passage : StringList) {
			System.out.println(passage.publish);
		}
		String strJSON="";
		ObjectMapper mapper=new ObjectMapper();
		try {
			strJSON=mapper.writeValueAsString(StringList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("strJSON="+strJSON);
		return strJSON;
	}
	//该方法的作用是获得用户又多少页
	@Override
	public String goPageByIssue(String username,int goPage) {
		goPage=goPage*10-10;
		List<Passage> StringList=passagedao.pageIssue(username,goPage);

		String strJSON="";
		ObjectMapper mapper=new ObjectMapper();
		try {
			strJSON=mapper.writeValueAsString(StringList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strJSON;
	}
	//writePassage方法的作用是讲文章保存为txt格式 并将文章信息存入数据库 例如文章保存的路径等
	@Override
	public String writePassage(String username, String Ptitle, String Pclassify,String passageStr,String pbrief) {
		List<Passage> passage1 = passagedao.selectByAuthorAndName(username, Ptitle);

		String fal="";
		if(passage1.toString().equals("[]")){
		
			//定义文章路径
			String passageName =System.getProperties().getProperty("user.home");
			passageName=passageName+File.separator+"passagetxt"+File.separator+username+"@"+Ptitle+".txt";
			System.out.println(passageName);
			//将文章类容写到响应的文章路径中
			try {
				passWrite.writeTotxt(passageStr, passageName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//获取当前日期
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
			String nowdata=df.format(new Date());
			int userId = LoginDao.SelectIdByuserName(username);//获得用户id

			passagedao.insertpassage(new Passage(userId, "", passageName, Ptitle, nowdata, Pclassify, pbrief, 0, 0,false));
	
			fal="true";
		}else{
			fal="false";
			System.out.println("錯誤的執行了這個方法");
		}
		return fal;
	}

	@Override
	public String updatewritePassage(String username, String Ptitle, String Pclassify,String passageStr,String pbrief) {
		
		List<Passage> passage1 = passagedao.selectByAuthorAndName(username, Ptitle);
		String fal="";
		if(passage1.toString().equals("[]")){	
			fal="true";
		}else{
			//定义文章路径
			String passageName =System.getProperties().getProperty("user.home");
			passageName =passageName+File.separator+"passagetxt"+File.separator+username+"@"+Ptitle+".txt";
			//将文章类容写到响应的文章路径中
			try {
				System.out.println(passageName);
				passWrite.writeTotxt(passageStr, passageName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//获取当前日期
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			System.out.println("当前日期："+df.format(new Date()));// new Date()为获取当前系统时间
			String nowdata=df.format(new Date());
			int userId = LoginDao.SelectIdByuserName(username);//获得用户id

			Passage pass=new Passage(userId, "", passageName, Ptitle, nowdata, Pclassify, pbrief, 0, 0,false);
		
			passagedao.updatepassage(pass);//;
	
			fal="true";
		}
		return fal;
	}

}
