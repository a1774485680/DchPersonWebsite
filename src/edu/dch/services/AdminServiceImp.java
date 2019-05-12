package edu.dch.services;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import edu.dch.bean.Passage;
import edu.dch.dao.IPassageDao;
import edu.dch.dao.IadminDao;
import edu.dch.utils.MybatisSqlSessionutils;

@Component("AdminServiceImp")
public class AdminServiceImp implements IAdminService {
	@Resource(name="iadminDao")
	public IadminDao admindao;
	@Resource(name="IPassageDao")
	public IPassageDao passdao;
	public AdminServiceImp(){
	}
	@Override
	public int verifyAdmin(String name, String password) {
		// TODO Auto-generated method stub
		int a=admindao.selectbynameAndpassword(name,password);
		
		return a;
	}
	@Override
	public int changeStatus(String title, String author) {
		// TODO Auto-generated method stub
		int a=1;
		passdao.changeStatus(author,title);


		return a;
	}
	@Override
	public String PassageLoad(int page) {

		
		page=page*10-10;//因为每页有10篇文章，从 0行 10行 以此类推查起
		List<Passage> selectPassage = passdao.selectPassageByPageByPublish(page);//得到数据
	
		//创建一个json对象
		ObjectMapper mapper=new ObjectMapper();
			String a="";//json返回的数组
			try {
				a = mapper.writeValueAsString(selectPassage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return a;
	}
	@Override
	public int allCountPassage(String classify) {
		// TODO Auto-generated method stub
				String classify2 = this.getClassify(classify);//获得数据库中相应类别所对应的名称
				
				int allCount;
				int Allpage=0;
				if(classify2.equals("所有")){
					allCount=passdao.allcountByPublish();
				}else{	
				
					 allCount = passdao.allcountpassageByPublish(classify2);//调用dao层的方法获得文章的所有数量
					// 定义页码数量
					//定义 i,j临时变量算出到底页码数量为多少
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
	public String getClassify(String classify){
		
		String[] classifys={"所有","java","spring","springmvc","mybatis","springboot","mysql","redis"};
		String[] Categorys=new String[8];
		for(int i=0;i<8;i++){
			Categorys[i]="classify-"+(i+1);
		}//取得所有的classify-*
		int SerialNumber=0;
		for(int i=0;i<8;i++){
			if(Categorys[i].equals(classify)){
				SerialNumber=i;
			}
		}
		String s=classifys[SerialNumber];
		return s;
	}
	@Override
	public String classifyPassage(String classify, int page) {
		String classify2 = this.getClassify(classify);//获得数据库中相应类别所对应的名称
		String strJson;
		if(classify2.equals("所有")){
			strJson = this.PassageLoad(page);
			
		}else{
			page=page*10-10;//因为每页有10篇文章，从 0行 10行 以此类推查起
			
			List<Passage> PassageByPageandClassify = passdao.selectPassageByPageandClassifyByPublish(classify2, page);

			
			
			// TODO Auto-generated method stub
			ObjectMapper mapper=new ObjectMapper();
			String a="";//json返回的数组
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
}
