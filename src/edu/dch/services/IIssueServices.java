package edu.dch.services;

public interface IIssueServices {

	String Allpage(String name);

	String PassageLoad(String username);

	String goPageByIssue(String string, int goPage);
	void writePassage(String username,String Ptitle,String Pclassify,String passageStr,String pbrief);
}
