package edu.dch.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.dch.bean.Userlogin;
import edu.dch.dao.IUserLoginDao;
import edu.dch.utils.MybatisSqlSessionutils;


public class Testone {
	public IUserLoginDao  userLoginDao;
	private SqlSession session;
	
	@Before
	public void before() {
		session=MybatisSqlSessionutils.GetSqlSession();
		 userLoginDao=session.getMapper(IUserLoginDao.class);
	}
	@After
	public void after() {
		session.commit();
	}
	@Test
	public void testone() {
		
		 Userlogin selectUserName = userLoginDao.SelectUserName("001");

		System.out.println(selectUserName);
	}
	@Test
	public void testone123() {
		userLoginDao.insertUserlogin(new Userlogin("ces","12"));
	}
}
