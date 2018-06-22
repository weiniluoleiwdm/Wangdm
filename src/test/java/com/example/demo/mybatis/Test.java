package com.example.demo.mybatis;

import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.mapper.dao.UserMapper;

/**  
* @Title: Test.java  
* @Package com.example.demo.mybatis  
* @Description: TODO
* @author wdm  
* @date 2018年6月21日  下午3:07:36
* @version V1.0  
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
	
	@Autowired
	private UserMapper UserMapper;
	
	@org.junit.Test
	public void testQuery() throws Exception {
		List<Map<String, Object>> users = UserMapper.getUserDwList();
		System.out.println(users.toString());
	}
	


}
