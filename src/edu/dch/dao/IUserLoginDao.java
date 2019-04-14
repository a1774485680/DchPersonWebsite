package edu.dch.dao;

import java.util.List;

import edu.dch.bean.Userlogin;

public interface IUserLoginDao {
	void insertUserlogin(Userlogin user);
	Userlogin SelectUserName(String UserName);
}
