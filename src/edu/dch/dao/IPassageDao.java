package edu.dch.dao;

import java.util.List;

import edu.dch.bean.Passage;

public interface IPassageDao {
	void insertpassage(Passage passage);
	List<Passage> SelectPassage();
}
