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
		passdao.insertpassage(new Passage(3,"sa","c:/sa/sa/sa/sa","≤‚ ‘","2019/4/1","spring","’‚∏ˆºÚΩÈ∫‹ºÚ∂Ã",12,12));
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
		a.PassageLoad(1);
	}
	@Test
	public void test01() {
		int allcountpassage = passdao.allcountpassage("java");
		System.out.println(allcountpassage);
	}
	@Test
	public void test02() {
		List<Passage> selectPassageByPage = passdao.selectPassageByPage(30);
		for(Passage a:selectPassageByPage){
			System.out.println(a.toString());
		}
	}
	@Test
	public void test03() {
		PassageLoadImp a=new PassageLoadImp();
		String classify = a.getClassify("classify-7");
		System.out.println(classify);
	}
	@Test
	public void test04() {
		List<Passage> selectPassageByPageandClassify = passdao.selectPassageByPageandClassify("java", 2);
		for(Passage a:selectPassageByPageandClassify){
			
			System.out.println(a.toString());
		}
	}
}
