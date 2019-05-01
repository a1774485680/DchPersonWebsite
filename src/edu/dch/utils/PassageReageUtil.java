package edu.dch.utils;

import java.io.BufferedReader;
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
				FileReader	fr=new FileReader("D:\\Program Files\\eclipse-oxygen\\workspace\\MyFirstWeb\\PassageTxt"+"\\"+passageName+".txt");
				BufferedReader bufr = new BufferedReader(fr);
				while((passageStr = bufr.readLine())!=null){
					
					s=passageStr;
					
				}
				fr.close();	
				System.out.println("读完成");
				return s;
	}
	public String txtPassageToRead2(String passageName) throws IOException{
		//建立通道
		String passageStr="";
		String s="";
		FileReader	fr=new FileReader("D:\\Program Files\\eclipse-oxygen\\workspace\\MyFirstWeb\\PassageTxt"+"\\"+passageName+".txt");
		BufferedReader bufr = new BufferedReader(fr);
		while((passageStr = bufr.readLine())!=null){
			
			s=passageStr;
			System.out.println(passageStr);
		}
		fr.close();	
		System.out.println(s);
		System.out.println("读完成");
		return passageStr;
	}
	public String txtPassageToRead3(String passageName) throws IOException{
		//建立通道
		String passageStr="";		
		FileReader	fr=new FileReader("D:\\Program Files\\eclipse-oxygen\\workspace\\MyFirstWeb\\PassageTxt"+"\\"+passageName+".txt");
		
		int cc=fr.read();
		while(cc!=-1){
			
			passageStr=passageStr+((char)cc);
			cc=fr.read();
		}
		fr.close();	
		System.out.println("读完成");
		return passageStr;
	}
}
