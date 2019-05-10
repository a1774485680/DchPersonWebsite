package edu.dch.services;

public interface IAdminService {

	int verifyAdmin(String name, String password);

	int changeStatus(String title, String author);

	String PassageLoad(int i);

	int allCountPassage(String nowCategory);

	String classifyPassage(String nowCategory, int goPage);

}
