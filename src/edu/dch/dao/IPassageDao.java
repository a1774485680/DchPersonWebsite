package edu.dch.dao;

import java.util.List;

import edu.dch.bean.Passage;

public interface IPassageDao {
	void insertpassage(Passage passage);
	List<Passage> SelectPassage();
	int allcountpassage(String classify);
	int allcount();
	List<Passage> selectPassageByPage(int page);
	List<Passage> selectPassageByPageandClassify(String classify,int page);
	int CountAllpage(String name);

	List<String> SelectByUserName(String name);

	List<String> pageIssue(String username, int goPage);
}
