package edu.dch.services;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import edu.dch.bean.Userlogin;
import edu.dch.dao.IUserLoginDao;
import edu.dch.utils.MybatisSqlSessionutils;
@Component("RegisterServiceImp")
public class RegisterServiceImp implements IRegisterService {
	public IUserLoginDao  userLoginDao;
	private SqlSession session;
	public RegisterServiceImp(){
		session=MybatisSqlSessionutils.GetSqlSession();
		 userLoginDao=session.getMapper(IUserLoginDao.class);
	}
	@Override
	public boolean usernameVerify(String name) {
		// TODO Auto-generated method stub
		Userlogin selectUserName = userLoginDao.SelectUserName(name);
	
		if(selectUserName==null){
			return false;
		}
		return true;
	}

	@Override
	public void userRegiser(Userlogin user) {
		// TODO Auto-generated method stub
		userLoginDao.insertUserlogin(user);
		session.commit();
	}

}
