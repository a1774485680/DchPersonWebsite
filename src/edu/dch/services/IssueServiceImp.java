package edu.dch.services;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.dch.bean.Passage;
import edu.dch.bean.Userlogin;
import edu.dch.dao.IPassageDao;
import edu.dch.dao.IUserLoginDao;
import edu.dch.utils.MybatisSqlSessionutils;

import edu.dch.utils.PassageWriteUtil;


@Component("IssueServiceImp")
public class IssueServiceImp implements IIssueServices {
	@Resource(name="IPassageDao")
	public IPassageDao passagedao;
	
	private PassageWriteUtil passWrite;
	@Resource(name="IUserLoginDao")
	private IUserLoginDao LoginDao;
	public IssueServiceImp() {
		super();
		
		passWrite=new PassageWriteUtil();
	}
	
	//����ж���ҳ���� �Ѹ�ҳ����Ϊjson��ʽ
	@Override
	public String Allpage(String name) {
		// TODO Auto-generated method stub
		//���һ���ж�������
		int allCount = passagedao.CountAllpage(name);
		//����������Ϊҳ
		int Allpage=0;
		if(name==null){//���û�е�½ת����½����
			Allpage=-1;
		}else{
			//ת��
			int i=allCount/10;
			int j=allCount%10;
			if(j!=0){
				Allpage=i+1;
			}else{
				Allpage=i;
			}
		}
		//���õ���ҳת��Ϊ�ַ���
		HashMap<String,Object> map = new HashMap<String,Object>();
		ObjectMapper mapper=new ObjectMapper();
		map.put("count", Allpage);
		String s="";
		try {
			s=mapper.writeValueAsString(map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return s;
	}

	@Override
	public String PassageLoad(String username) {

		
		List<Passage> StringList= passagedao.SelectByUserName(username);

		for (Passage passage : StringList) {
			System.out.println(passage.publish);
		}
		String strJSON="";
		ObjectMapper mapper=new ObjectMapper();
		try {
			strJSON=mapper.writeValueAsString(StringList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("strJSON="+strJSON);
		return strJSON;
	}
	//�÷����������ǻ���û��ֶ���ҳ
	@Override
	public String goPageByIssue(String username,int goPage) {
		goPage=goPage*10-10;
		List<Passage> StringList=passagedao.pageIssue(username,goPage);

		String strJSON="";
		ObjectMapper mapper=new ObjectMapper();
		try {
			strJSON=mapper.writeValueAsString(StringList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strJSON;
	}
	//writePassage�����������ǽ����±���Ϊtxt��ʽ ����������Ϣ�������ݿ� �������±����·����
	@Override
	public String writePassage(String username, String Ptitle, String Pclassify,String passageStr,String pbrief) {
		List<Passage> passage1 = passagedao.selectByAuthorAndName(username, Ptitle);

		String fal="";
		if(passage1.toString().equals("[]")){
		
			//��������·��
			String passageName =System.getProperties().getProperty("user.home");
			passageName=passageName+File.separator+"passagetxt"+File.separator+username+"@"+Ptitle+".txt";
			System.out.println(passageName);
			//����������д����Ӧ������·����
			try {
				passWrite.writeTotxt(passageStr, passageName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//��ȡ��ǰ����
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
			String nowdata=df.format(new Date());
			int userId = LoginDao.SelectIdByuserName(username);//����û�id

			passagedao.insertpassage(new Passage(userId, "", passageName, Ptitle, nowdata, Pclassify, pbrief, 0, 0,false));
	
			fal="true";
		}else{
			fal="false";
			System.out.println("�e�`�Ĉ������@������");
		}
		return fal;
	}

	@Override
	public String updatewritePassage(String username, String Ptitle, String Pclassify,String passageStr,String pbrief) {
		
		List<Passage> passage1 = passagedao.selectByAuthorAndName(username, Ptitle);
		String fal="";
		if(passage1.toString().equals("[]")){	
			fal="true";
		}else{
			//��������·��
			String passageName =System.getProperties().getProperty("user.home");
			passageName =passageName+File.separator+"passagetxt"+File.separator+username+"@"+Ptitle+".txt";
			//����������д����Ӧ������·����
			try {
				System.out.println(passageName);
				passWrite.writeTotxt(passageStr, passageName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//��ȡ��ǰ����
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			System.out.println("��ǰ���ڣ�"+df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
			String nowdata=df.format(new Date());
			int userId = LoginDao.SelectIdByuserName(username);//����û�id

			Passage pass=new Passage(userId, "", passageName, Ptitle, nowdata, Pclassify, pbrief, 0, 0,false);
		
			passagedao.updatepassage(pass);//;
	
			fal="true";
		}
		return fal;
	}

}
