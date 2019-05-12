package edu.dch.services;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.stereotype.Component;

import edu.dch.bean.Passage;
import edu.dch.dao.IPassageDao;
import edu.dch.utils.MybatisSqlSessionutils;
import net.sf.json.JSONObject;
@Component("PassageLoadImp")
public class PassageLoadImp implements IPassageServices {
	@Resource(name="IPassageDao")
	public IPassageDao passdao;
	
	
	public String PassageLoad(int page) {
		
		
		page=page*10-10;//��Ϊÿҳ��10ƪ���£��� 0�� 10�� �Դ����Ʋ���
		List<Passage> selectPassage = passdao.selectPassageByPage(page);//�õ�����
	
		//����һ��json����
		ObjectMapper mapper=new ObjectMapper();
			String a="";//json���ص�����
			try {
				a = mapper.writeValueAsString(selectPassage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return a;
	}

	
	public int allCountPassage(String classify) {
		
		// TODO Auto-generated method stub
		String classify2 = this.getClassify(classify);//������ݿ�����Ӧ�������Ӧ������
		
		int allCount;
		int Allpage=0;
		if(classify2.equals("����")){
			allCount=passdao.allcount();
		}else{	
		
			 allCount = passdao.allcountpassage(classify2);//����dao��ķ���������µ���������
			// ����ҳ������
			//���� i,j��ʱ�����������ҳ������Ϊ����
		}
		
		int i=allCount/10;
		int j=allCount%10;
		if(j!=0){
			Allpage=i+1;
		}else{
			Allpage=i;
		}
		return Allpage;
	}

	@Override
	public String classifyPassage(String classify, int page) {
	
		
		String classify2 = this.getClassify(classify);//������ݿ�����Ӧ�������Ӧ������
		String strJson;
		if(classify2.equals("����")){
			strJson = this.PassageLoad(page);
			
		}else{
			page=page*10-10;//��Ϊÿҳ��10ƪ���£��� 0�� 10�� �Դ����Ʋ���
			
			List<Passage> PassageByPageandClassify = passdao.selectPassageByPageandClassify(classify2, page);
		
			
			
			// TODO Auto-generated method stub
			ObjectMapper mapper=new ObjectMapper();
			String a="";//json���ص�����
			try {
				a = mapper.writeValueAsString(PassageByPageandClassify);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			strJson= a;
		}
		return strJson;
	}

	public String getClassify(String classify){
		
		String[] classifys={"����","java","spring","springmvc","mybatis","springboot","mysql","redis"};
		String[] Categorys=new String[8];
		for(int i=0;i<8;i++){
			Categorys[i]="classify-"+(i+1);
		}//ȡ�����е�classify-*
		int SerialNumber=0;
		for(int i=0;i<8;i++){
			if(Categorys[i].equals(classify)){
				SerialNumber=i;
			}
		}
		String s=classifys[SerialNumber];
		return s;
	}
	
}
