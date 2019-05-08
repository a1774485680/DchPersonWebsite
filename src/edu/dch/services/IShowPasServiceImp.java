package edu.dch.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import edu.dch.bean.Passage;
import edu.dch.dao.IPassageDao;
import edu.dch.utils.MybatisSqlSessionutils;
import edu.dch.utils.PassageReageUtil;

@Component("IShowPasServiceImp")
public class IShowPasServiceImp implements IShowPassageServices {
	public PassageReageUtil passread;
	public IPassageDao passdao;
	private SqlSession session;
	public IShowPasServiceImp(){
		passread=new PassageReageUtil();
		session=MybatisSqlSessionutils.GetSqlSession();
		passdao=session.getMapper(IPassageDao.class);//dao层的初始化
	}
	@Override
	public String SelectByNameAndTitle(String name, String title) {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));
		String passName=name+"@"+title; 
		String txtPassageToRead="";
		try {
			txtPassageToRead = passread.txtPassageToRead(passName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return txtPassageToRead;    
		
	}
	//通过作者姓名和文章标题获得文章信息
	@Override
	public String SelectByNameAndTitleOfpassageinfrom(String name, String title) {
		// TODO Auto-generated method stub
		List<Passage> as= passdao.selectByAuthorAndName(name,title);
		//创建一个json对象
				ObjectMapper mapper=new ObjectMapper();
					String a="";//json返回的数组
					try {
						a = mapper.writeValueAsString(as);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					passdao.updateByAuthorAndNameForVisit(name,title);
					session.commit();
		return a;
	}

}
