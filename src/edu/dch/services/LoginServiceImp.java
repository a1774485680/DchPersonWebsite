package edu.dch.services;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import edu.dch.bean.Userlogin;

import edu.dch.dao.IUserLoginDao;
import edu.dch.utils.MybatisSqlSessionutils;
@Component("LoginServiceImp")
public class LoginServiceImp implements ILoginService {
	public IUserLoginDao loginDao;
	public SqlSession session;
	public LoginServiceImp() {
		super();
		session=MybatisSqlSessionutils.GetSqlSession();
		loginDao=session.getMapper(IUserLoginDao.class);
	}

	@Override
	public int VaildateByUserService(Userlogin user) {
		int validataByUser = loginDao.validataByUser(user);
		session.commit();
		return validataByUser;
		// TODO Auto-generated method stub
		
	}

}
