package edu.dch.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.stereotype.Component;
@Component("PassageReageUtil")
public class PassageReageUtil {
	
	public String txtPassageToRead(String passageName) throws IOException{
		//建立通道
				String passageStr="";
				String s="";
				String passagepath =System.getProperties().getProperty("user.home");
				passagepath=passagepath+File.separator+"passagetxt";
				FileReader	fr=new FileReader(passagepath+File.separator+passageName+".txt");
				BufferedReader bufr = new BufferedReader(fr);
				while((passageStr = bufr.readLine())!=null){
					
					s=passageStr;
					
				}
				fr.close();	
				System.out.println("读完成");
				return s;
	}
	
}
