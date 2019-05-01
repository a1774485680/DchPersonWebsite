package edu.dch.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.dch.bean.Passage;
import edu.dch.bean.Userlogin;
import edu.dch.dao.IPassageDao;
import edu.dch.dao.IUserLoginDao;
import edu.dch.utils.MybatisSqlSessionutils;

import edu.dch.utils.PassageWriteUtil;


@Component("IssueServiceImp")
public class IssueServiceImp implements IIssueServices {
	
	public IPassageDao passagedao;
	private SqlSession session,session1;
	private PassageWriteUtil passWrite;
	private IUserLoginDao LoginDao;
	public IssueServiceImp() {
		super();
		
		passWrite=new PassageWriteUtil();
		session=MybatisSqlSessionutils.GetSqlSession();
		LoginDao=(IUserLoginDao) session.getMapper(IUserLoginDao.class);
		session1=MybatisSqlSessionutils.GetSqlSession();
		passagedao=(IPassageDao) session1.getMapper(IPassageDao.class);
	}
	
	//获得有多少页并且 把该页设置为json格式
	@Override
	public String Allpage(String name) {
		// TODO Auto-generated method stub
		//获得一共有多少文章
		int allCount = passagedao.CountAllpage(name);
		session1.commit();
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
		System.out.println(s);
		return s;
	}

	@Override
	public String PassageLoad(String username) {
		List<String> StringList= passagedao.SelectByUserName(username);
		session1.commit();
		for ( String s: StringList) {
			System.out.println(s);
		}
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
	//该方法的作用是获得用户又多少页
	@Override
	public String goPageByIssue(String username,int goPage) {
		goPage=goPage*10-10;
		List<String> StringList=passagedao.pageIssue(username,goPage);
		session1.commit();
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
	public void writePassage(String username, String Ptitle, String Pclassify,String passageStr,String pbrief) {
		//定义文章路径
		String passageName ="D:\\Program Files\\eclipse-oxygen\\workspace\\MyFirstWeb\\PassageTxt\\"+username+"@"+Ptitle+".txt";
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
		session.commit();
		passagedao.insertpassage(new Passage(userId, "", passageName, Ptitle, nowdata, Pclassify, pbrief, 0, 0));
		session1.commit();
	}

}
