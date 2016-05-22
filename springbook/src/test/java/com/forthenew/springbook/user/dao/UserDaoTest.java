package com.forthenew.springbook.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.forthenew.springbook.user.User;
import com.forthenew.springbook.user.domain.Level;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/spring/application-config.xml")
public class UserDaoTest {
//	@Autowired
//	private ApplicationContext context;
	
	@Autowired
	private UserDao dao;
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setup() {
//		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-config.xml");
//		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//		dao = context.getBean("userDao", UserDao.class);
		user1 = new User("gyumee", "박성철", "springno1", "email", Level.BASIC, 1, 0);
		user2 = new User("leegw700", "이길원", "springno2", "email", Level.SILVER, 55, 10);
		user3 = new User("bumjin", "박범진", "springno3", "email", Level.GOLD, 100, 40);
	}
	@Test
	public void addAndGet() throws SQLException {
		
		dao.deleteAll();
		assertThat(new Integer(0), is(dao.getCount()));
		
		dao.add(user1);
		dao.add(user2);
		assertThat(new Integer(2), is(dao.getCount()));
		
		User userget1 = dao.get(user1.getId());
		checkSameUser(userget1, user1);
		
		User userget2 = dao.get(user2.getId());
		checkSameUser(userget2, user2);
	}
	
	@Test
	public void count() throws SQLException {
		dao.deleteAll();
		assertThat(new Integer(0), is(dao.getCount()));
		
		dao.add(user1);
		assertThat(new Integer(1), is(dao.getCount()));
		
		dao.add(user2);
		assertThat(new Integer(2), is(dao.getCount()));
		
		dao.add(user3);
		assertThat(new Integer(3), is(dao.getCount()));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {
		dao.deleteAll();
		assertThat(new Integer(0), is(dao.getCount()));

		dao.get("unkonwn_id");
	}
	
	@Test
	public void getAll() throws SQLException {
		dao.deleteAll();
		
		List<User> user0 = dao.getAll();
		assertThat(user0.size(), is(0));
		
		dao.add(user1);
		List<User> users1 = dao.getAll();
		assertThat(users1.size(), is(1));
		checkSameUser(user1, users1.get(0));
		
		dao.add(user2);
		List<User> users2 = dao.getAll();
		assertThat(users2.size(), is(2));
		checkSameUser(user1, users2.get(0));
		checkSameUser(user2, users2.get(1));
		
		dao.add(user3);
		List<User> users3 = dao.getAll();
		assertThat(users3.size(), is(3));
		checkSameUser(user3, users3.get(0));
		checkSameUser(user1, users3.get(1));
		checkSameUser(user2, users3.get(2));
	}
	
	private void checkSameUser(User user1, User user2) {
		assertThat(user1.getId(), is(user2.getId()));
//		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
		assertThat(user1.getLevel(), is(user2.getLevel()));
		assertThat(user1.getLogin(), is(user2.getLogin()));
		assertThat(user1.getRecommend(), is(user2.getRecommend()));
	}
	
	@Test(expected=DataAccessException.class)
	public void duplicatKey() {
		dao.deleteAll();
		
		dao.add(user1);
		dao.add(user1);
	}
	
	@Test
	public void update() {
		dao.deleteAll();
		
		dao.add(user1);
		dao.add(user2);
		
		user1.setName("오민규");
		user1.setPassword("springno6");
		user1.setLevel(Level.GOLD);
		user1.setLogin(1000);
		user1.setRecommend(999);
//		user1.update(user1);
		
		User user1update = dao.get(user1.getId());
		checkSameUser(user1, user1update);
		User user2same = dao.get(user2.getId());
		checkSameUser(user2, user2same);
	}
}
