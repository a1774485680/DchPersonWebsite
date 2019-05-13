package edu.dch.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.junit.Test;
import org.springframework.stereotype.Component;
@Component("PassageWriteUtil")
public class PassageWriteUtil {
	
	
	public void writeTotxt(String passgaeStr,String passageName) throws IOException{
		//建立刘通道
		String passagepath =System.getProperties().getProperty("user.home");
		passagepath=passagepath+File.separator+"passagetxt";
		File file = new File(passagepath);
	
       if (!file.exists()) {
           file.mkdirs();
       }
       Writer out=new FileWriter(passageName);
       BufferedWriter bw =new BufferedWriter(out);
       bw.write(passgaeStr);
       bw.flush();
		       
	}
}
