package edu.dch.services;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import edu.dch.bean.Userlogin;
import edu.dch.dao.IUserLoginDao;
import edu.dch.dao.IadminDao;
import edu.dch.utils.MybatisSqlSessionutils;
@Component("RegisterServiceImp")
public class RegisterServiceImp implements IRegisterService {
	@Resource(name="IUserLoginDao")
	public IUserLoginDao  userLoginDao;
	@Resource(name="iadminDao")
	public IadminDao adminDao;
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

	}
	@Override
	public boolean identilyVerify(String myidentily) {
		// TODO Auto-generated method stub
		int identilyVerify = adminDao.identilyVerify(myidentily);
		boolean fal=false;
		if(identilyVerify==1){
			fal=true;
		}
		return fal;
	}

}
