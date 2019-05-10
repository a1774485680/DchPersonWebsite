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

	List<Passage> SelectByUserName(String name);

	List<Passage> pageIssue(String username, int goPage);
	List<Passage> selectByAuthorAndName(String name, String title);
	void updateByAuthorAndNameForVisit(String name, String title);
	void updatepassage(Passage passage);
	void changeStatus(String author, String title);
	List<Passage> selectPassageByPageByPublish(int page);
	int allcountpassageByPublish(String classify2);
	List<Passage> selectPassageByPageandClassifyByPublish(String classify2, int page);
	int allcountByPublish();
}
