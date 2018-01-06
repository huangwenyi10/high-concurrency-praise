package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HighConcurrencyPraiseApplicationTests {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Test
	public void contextLoads() {
		String sql = "select DATABASE()";
		jdbcTemplate.execute(sql);
		System.out.println("success");
	}

}
