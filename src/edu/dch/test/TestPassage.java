package edu.dch.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.dch.bean.Passage;

import edu.dch.dao.IPassageDao;

import edu.dch.services.IPassageServices;
import edu.dch.services.PassageLoadImp;
import edu.dch.utils.MybatisSqlSessionutils;

public class TestPassage {
	public IPassageDao passdao;
	private SqlSession session;
	IPassageServices passageService;
	@Before
	public void before() {
		session=MybatisSqlSessionutils.GetSqlSession();
		passdao=session.getMapper(IPassageDao.class);
		ApplicationContext app=new ClassPathXmlApplicationContext("resource/spring-All.xml");
		passageService=(IPassageServices)app.getBean("PassageLoadImp");
	}
	@After
	public void after() {
		session.commit();
	}
	@Test
	public void testone() {
		passdao.insertpassage(new Passage(3,"sa","c:/sa/sa/sa/sa","测试","2019/4/1","spring","这个简介很简短",12,12));
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
		int allcountpassage = passdao.allcountpassage("spring");
		System.out.println(allcountpassage);
		int allcountpassage1 = passdao.allcountpassage("spring");
		System.out.println(allcountpassage1);
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
	
	public void test05(String NowCategory) {
		int allCount = passageService.allCountPassage(NowCategory);
		System.out.println("现在分类一共有的页数"+allCount);
		String upPageJson = passageService.classifyPassage(NowCategory, 1);
		System.out.println(upPageJson);
	}

	public void test06(String NowCategory) {
		int allCount = passageService.allCountPassage(NowCategory);
		System.out.println("现在分类一共有的页数"+allCount);
		String upPageJson = passageService.classifyPassage(NowCategory, 1);
		System.out.println(upPageJson);
	}
	@Test
	public void test07() {
		for(int i=1;i<10;i++){
			System.out.println();
			System.out.println();
			System.out.println();
			this.test05("classify-2");
			this.test06("classify-3");
			System.out.println(i);
		}
	}
	@Test
	public void test08() {
		for(int i=1;i<10;i++){
			System.out.println();
			System.out.println();
			System.out.println();
			this.test05("classify-2");
			this.test06("classify-3");
			System.out.println(i);
		}
	}
}
