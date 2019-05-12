package edu.dch.services;


import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import edu.dch.bean.Userlogin;

import edu.dch.dao.IUserLoginDao;
import edu.dch.utils.MybatisSqlSessionutils;
@Component("LoginServiceImp")
public class LoginServiceImp implements ILoginService {
	@Resource(name="IUserLoginDao")
	public IUserLoginDao loginDao;

	@Override
	public int VaildateByUserService(Userlogin user) {

		int validataByUser = loginDao.validataByUser(user);
		System.out.println("ох  "+validataByUser);

		System.out.println("╨С  "+validataByUser);
		return validataByUser;
		// TODO Auto-generated method stub
		
	}

}
