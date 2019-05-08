package edu.dch.services;

public interface IIssueServices {

	String Allpage(String name);

	String PassageLoad(String username);

	String goPageByIssue(String string, int goPage);
	String writePassage(String username,String Ptitle,String Pclassify,String passageStr,String pbrief);

	String updatewritePassage(String username, String ptitle, String pclassify, String passageStr, String pbrief);
}
