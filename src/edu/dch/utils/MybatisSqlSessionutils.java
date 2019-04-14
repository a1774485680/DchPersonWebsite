package edu.dch.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisSqlSessionutils {
	public static SqlSessionFactory sqlsessionfactory;
	public static SqlSession GetSqlSession() {
		InputStream inputstrem;
		try {
			if(sqlsessionfactory==null) {
				inputstrem = Resources.getResourceAsStream("Mybatis.xml");
				sqlsessionfactory=new SqlSessionFactoryBuilder().build(inputstrem);
				}
			return sqlsessionfactory.openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
		
		}
}
