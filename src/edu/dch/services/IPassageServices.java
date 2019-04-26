package edu.dch.services;

public interface IPassageServices {
	public String PassageLoad(int page);
	public int allCountPassage(String nowclassify);
	public String classifyPassage(String classify,int page);
}
