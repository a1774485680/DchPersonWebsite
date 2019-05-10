package edu.dch.services;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import edu.dch.bean.Userlogin;
import edu.dch.dao.IUserLoginDao;
import edu.dch.dao.IadminDao;
import edu.dch.utils.MybatisSqlSessionutils;
@Component("RegisterServiceImp")
public class RegisterServiceImp implements IRegisterService {
	public IUserLoginDao  userLoginDao;
	public IadminDao adminDao;
	private SqlSession session,session2;
	public RegisterServiceImp(){
		session=MybatisSqlSessionutils.GetSqlSession();
		 userLoginDao=session.getMapper(IUserLoginDao.class);
		 session2=MybatisSqlSessionutils.GetSqlSession();
		 adminDao=session.getMapper(IadminDao.class);
		 System.out.println("");
	}
	@Override
	public boolean usernameVerify(String name) {
		// TODO Auto-generated method stub
		Userlogin selectUserName = userLoginDao.SelectUserName(name);
		session.commit();
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
	@Override
	public boolean identilyVerify(String myidentily) {
		// TODO Auto-generated method stub
		int identilyVerify = adminDao.identilyVerify(myidentily);
		session2.commit();
		boolean fal=false;
		if(identilyVerify==1){
			fal=true;
		}
		return fal;
	}

}
