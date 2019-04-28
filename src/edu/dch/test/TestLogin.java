package edu.dch.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.dch.bean.Userlogin;
import edu.dch.dao.ILoginDao;
import edu.dch.dao.IPassageDao;
import edu.dch.utils.MybatisSqlSessionutils;

public class TestLogin {
	public ILoginDao loginDao;
	private SqlSession session;
	
	@Before
	public void before() {
		session=MybatisSqlSessionutils.GetSqlSession();
		loginDao=session.getMapper(ILoginDao.class);
	}
	@After
	public void after() {
		session.commit();
	}
	@Test
	public void test01(){
		int validataByUser = loginDao.validataByUser(new Userlogin("sa","123456a"));
		System.out.println(validataByUser);
	}
}
