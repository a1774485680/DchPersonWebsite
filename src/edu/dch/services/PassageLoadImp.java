package edu.dch.services;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.stereotype.Component;

import edu.dch.bean.Passage;
import edu.dch.dao.IPassageDao;
import edu.dch.utils.MybatisSqlSessionutils;
import net.sf.json.JSONObject;
@Component("PassageLoadImp")
public class PassageLoadImp implements IPassageServices {
	public IPassageDao passdao;
	private SqlSession session;
	public PassageLoadImp(){
		session=MybatisSqlSessionutils.GetSqlSession();
		passdao=session.getMapper(IPassageDao.class);
	}
	
	public String PassageLoad() {
		List<Passage> selectPassage = passdao.SelectPassage();//得到数据
		session.commit();
		//创建一个json对象
		ObjectMapper mapper=new ObjectMapper();
		
		
			String a="";
			try {
				a = mapper.writeValueAsString(selectPassage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println(a);
		
		return null;
	}
	
}
