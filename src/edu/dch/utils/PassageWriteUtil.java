package edu.dch.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.junit.Test;
import org.springframework.stereotype.Component;
@Component("PassageWriteUtil")
public class PassageWriteUtil {
	public void writeTotxt1(String passgaeStr,String passageName) throws IOException{
		FileWriter fw=null;
		 try {
			fw=new FileWriter(passageName);
			fw.write(passgaeStr);
			System.out.println("写完成");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fw!=null){
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
	}
	
	public void writeTotxt(String passgaeStr,String passageName) throws IOException{
		//建立刘通道
				Writer out=new FileWriter(passageName);
				BufferedWriter bw =new BufferedWriter(out);
				bw.write(passgaeStr);
				bw.flush();
		System.out.println(passgaeStr.length());
	}	
}
