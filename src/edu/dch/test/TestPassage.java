package edu.dch.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.dch.bean.Passage;
import edu.dch.bean.Userlogin;
import edu.dch.dao.IPassageDao;
import edu.dch.dao.IUserLoginDao;
import edu.dch.services.PassageLoadImp;
import edu.dch.utils.MybatisSqlSessionutils;

public class TestPassage {
	public IPassageDao passdao;
	private SqlSession session;
	
	@Before
	public void before() {
		session=MybatisSqlSessionutils.GetSqlSession();
		passdao=session.getMapper(IPassageDao.class);
	}
	@After
	public void after() {
		session.commit();
	}
	@Test
	public void testone() {
		passdao.insertpassage(new Passage(1,"sa","c:/sa/sa/sa/sa","ÖÐ¹úÈË1466565","2019/4/1",12,12,12));
	}
	@Test
	public void testone123() {
		List<Passage> selectPassage = passdao.SelectPassage();
		for(Passage a:selectPassage){
			System.out.println(a.toString());
		}
	}
	@Test
	public void testone2() {
		PassageLoadImp a=new PassageLoadImp();
		a.PassageLoad();
	}
}
