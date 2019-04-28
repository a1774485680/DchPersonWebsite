package edu.dch.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import edu.dch.bean.Passage;
import edu.dch.dao.IIssueDao;
import edu.dch.dao.IPassageDao;
import edu.dch.utils.MybatisSqlSessionutils;


@Component("IssueServiceImp")
public class IssueServiceImp implements IIssueServices {
	public IIssueDao issueDao;
	private SqlSession session;
	public IssueServiceImp() {
		super();
		session=MybatisSqlSessionutils.GetSqlSession();
		issueDao=(IIssueDao) session.getMapper(IIssueDao.class);
	}
	
	//获得有多少页并且 把该页设置为json格式
	@Override
	public String Allpage(String name) {
		// TODO Auto-generated method stub
		//获得一共有多少文章
		int allCount = issueDao.CountAllpage(name);
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
		List<String> StringList= issueDao.SelectByUserName(username);
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

	@Override
	public String goPageByIssue(String username,int goPage) {
		goPage=goPage*10-10;
		List<String> StringList=issueDao.pageIssue(username,goPage);
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

}
