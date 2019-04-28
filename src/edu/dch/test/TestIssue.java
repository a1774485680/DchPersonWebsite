package edu.dch.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.dch.bean.Userlogin;
import edu.dch.dao.IIssueDao;
import edu.dch.services.IssueServiceImp;
import edu.dch.utils.MybatisSqlSessionutils;

public class TestIssue {
	public IIssueDao issueDao;
	private SqlSession session;
	@Before
	public void before() {
		session=MybatisSqlSessionutils.GetSqlSession();
		issueDao=session.getMapper(IIssueDao.class);
	}
	@After
	public void after() {
		session.commit();
	}
	@Test
	public void test01(){
		int validataByUser = issueDao.CountAllpage("sa");
		System.out.println(validataByUser);
	}
	@Test
	public void test02(){
		IssueServiceImp s= new IssueServiceImp();
		s.Allpage("sa");
		//System.out.println(validataByUser);
	}
	@Test
	public void test03(){
		IssueServiceImp s= new IssueServiceImp();
		String passageLoad = s.PassageLoad("sa");
		System.out.println(passageLoad);
		//System.out.println(validataByUser);
	}
	@Test
	public void test05(){
		IssueServiceImp s= new IssueServiceImp();
		String passageLoad = s.goPageByIssue("sa", 7);
		System.out.println(passageLoad);
		//System.out.println(validataByUser);
	}
}
