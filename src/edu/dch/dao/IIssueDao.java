package edu.dch.dao;

import java.util.List;

import edu.dch.bean.Passage;

public interface IIssueDao {

	int CountAllpage(String name);

	List<String> SelectByUserName(String name);

	List<String> pageIssue(String username, int goPage);

}
