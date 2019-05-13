package edu.dch.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.dch.bean.Userlogin;
import edu.dch.dao.IUserLoginDao;
import edu.dch.services.LoginServiceImp;
import edu.dch.utils.MybatisSqlSessionutils;
import edu.dch.utils.PassageReageUtil;


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
	@Test 
	public void testxx() throws IOException{
		PassageReageUtil as=new PassageReageUtil();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));
		as.txtPassageToRead("sa@图片5");
		SimpleDateFormat df11 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df11.format(new Date()));
	}
	@Test 
	public void test01() throws IOException{
		LoginServiceImp a=new LoginServiceImp();
		int vaildateByUserService = a.VaildateByUserService(new Userlogin("110", "123456a"));
		System.out.println(vaildateByUserService);
	}
	@Test 
	public void test02(){
		String userHome = System.getProperties().getProperty("user.home");
		System.out.println(userHome);
	}
	
}
