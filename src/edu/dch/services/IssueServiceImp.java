package edu.dch.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.dch.bean.Passage;
import edu.dch.bean.Userlogin;
import edu.dch.dao.IPassageDao;
import edu.dch.dao.IUserLoginDao;
import edu.dch.utils.MybatisSqlSessionutils;

import edu.dch.utils.PassageWriteUtil;


@Component("IssueServiceImp")
public class IssueServiceImp implements IIssueServices {
	
	public IPassageDao passagedao;
	private SqlSession session,session1;
	private PassageWriteUtil passWrite;
	private IUserLoginDao LoginDao;
	public IssueServiceImp() {
		super();
		
		passWrite=new PassageWriteUtil();
		session=MybatisSqlSessionutils.GetSqlSession();
		LoginDao=(IUserLoginDao) session.getMapper(IUserLoginDao.class);
		session1=MybatisSqlSessionutils.GetSqlSession();
		passagedao=(IPassageDao) session1.getMapper(IPassageDao.class);
	}
	
	//����ж���ҳ���� �Ѹ�ҳ����Ϊjson��ʽ
	@Override
	public String Allpage(String name) {
		// TODO Auto-generated method stub
		//���һ���ж�������
		int allCount = passagedao.CountAllpage(name);
		session1.commit();
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
		System.out.println(s);
		return s;
	}

	@Override
	public String PassageLoad(String username) {
		List<String> StringList= passagedao.SelectByUserName(username);
		session1.commit();
		for ( String s: StringList) {
			System.out.println(s);
		}
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
	//�÷����������ǻ���û��ֶ���ҳ
	@Override
	public String goPageByIssue(String username,int goPage) {
		goPage=goPage*10-10;
		List<String> StringList=passagedao.pageIssue(username,goPage);
		session1.commit();
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
		session1.commit();
		String fal="";
		if(passage1.toString().equals("[]")){
			System.out.println("�������@������");
			//��������·��
			String passageName ="D:\\Program Files\\eclipse-oxygen\\workspace\\MyFirstWeb\\PassageTxt\\"+username+"@"+Ptitle+".txt";
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
			session.commit();
			passagedao.insertpassage(new Passage(userId, "", passageName, Ptitle, nowdata, Pclassify, pbrief, 0, 0));
			session1.commit();
			fal="true";
		}else{
			fal="false";
			System.out.println("�e�`�Ĉ������@������");
		}
		return fal;
	}

	@Override
	public String updatewritePassage(String username, String Ptitle, String Pclassify,String passageStr,String pbrief) {
		session=MybatisSqlSessionutils.GetSqlSession();
		LoginDao=(IUserLoginDao) session.getMapper(IUserLoginDao.class);
		session1=MybatisSqlSessionutils.GetSqlSession();
		passagedao=(IPassageDao) session1.getMapper(IPassageDao.class);
		List<Passage> passage1 = passagedao.selectByAuthorAndName(username, Ptitle);
		String fal="";
		if(passage1.toString().equals("[]")){	
			fal="true";
		}else{
			//��������·��
			String passageName ="D:\\Program Files\\eclipse-oxygen\\workspace\\MyFirstWeb\\PassageTxt\\"+username+"@"+Ptitle+".txt";
			//����������д����Ӧ������·����
			try {
				System.out.println("passageStr="+passageStr);
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
			session.commit();
			Passage pass=new Passage(userId, "", passageName, Ptitle, nowdata, Pclassify, pbrief, 0, 0);
			System.out.println(pass);
			passagedao.updatepassage(pass);//;
			session1.commit();
			fal="true";
		}
		return fal;
	}

}
