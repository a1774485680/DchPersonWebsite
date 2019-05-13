package edu.dch.services;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.dch.bean.Passage;
import edu.dch.dao.IPassageDao;
import edu.dch.utils.MybatisSqlSessionutils;
import net.sf.json.JSONObject;
@Component("PassageLoadImp2")
public class PassageLoadImp2 implements IPassageServices2 {
	@Resource(name="IPassageDao")
	public IPassageDao passdao;

	public void setPassdao(IPassageDao passdao) {
		this.passdao = passdao;
	}
	public void test() {
		List<Passage> selectPassage = passdao.SelectPassage();
		for (Passage passage : selectPassage) {
			System.out.println(passage.toString());
		}
	}
	
}
