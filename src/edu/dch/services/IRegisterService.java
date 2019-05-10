package edu.dch.services;

import edu.dch.bean.Userlogin;

public interface IRegisterService {
	public boolean usernameVerify(String name);
	public void userRegiser(Userlogin user);
	public boolean identilyVerify(String myidentily);
}
