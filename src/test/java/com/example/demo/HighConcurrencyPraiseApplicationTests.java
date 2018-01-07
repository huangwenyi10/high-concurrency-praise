package com.example.demo;

import com.example.demo.model.Mood;
import com.example.demo.model.User;
import com.example.demo.model.UserMoodPraiseRel;
import com.example.demo.repository.MoodRepository;
import com.example.demo.repository.UserMoodPraiseRelRepository;
import com.example.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

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

	@Resource
	private UserRepository userRepository;
	@Resource
	private MoodRepository moodRepository;
	@Resource
	private UserMoodPraiseRelRepository userMoodPraiseRelRepository;

	@Test
	public void testSpringDataJpa(){
		List<User> userList =  userRepository.findAll();
		User user = userRepository.findOne("1");
		long count = userRepository.count();
		System.out.println(userList);
		System.out.println(count);

		List<Mood> moodList = moodRepository.findAll();
		System.out.println(moodList);

		List<UserMoodPraiseRel> userMoodPraiseRels =  userMoodPraiseRelRepository.findAll();
		System.out.println(userMoodPraiseRels);
	}



}
