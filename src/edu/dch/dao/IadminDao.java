package edu.dch.dao;

public interface IadminDao {
	int identilyVerify(String identily);

	int selectbynameAndpassword(String name, String password);
}
