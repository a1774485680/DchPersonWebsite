package edu.dch.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import edu.dch.utils.PassageReageUtil;

@Component("IShowPasServiceImp")
public class IShowPasServiceImp implements IShowPassageServices {
	public PassageReageUtil passread;
	public IShowPasServiceImp(){
		passread=new PassageReageUtil();
	}
	@Override
	public String SelectByNameAndTitle(String name, String title) {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));
		String passName=name+"@"+title; 
		String txtPassageToRead="";
		try {
			txtPassageToRead = passread.txtPassageToRead(passName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return txtPassageToRead;    
		
	}

}
