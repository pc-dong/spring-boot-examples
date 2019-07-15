package cn.dpc.mybatis.mapper;

import cn.dpc.mybatis.AbstractIntegrationTest;
import cn.dpc.mybatis.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Random;

import static org.junit.Assert.*;

@Transactional
public class UserMapperTest extends AbstractIntegrationTest {
	@Autowired
	private UserMapper userMapper;

	@Test
	public void testInsertUser() {
		Integer id = new Random(100000).nextInt();
		insertUser(id);
		assertNotNull(id.equals(userMapper.selectByPrimaryKey(id).getId()));
	}

	private void insertUser(Integer id) {
		User user = new User();
		user.setId(id);
		user.setUsername("admin");
		userMapper.insert(user);
	}

	@Test
	public void testPageHelper() {
		for (int i = 1; i <= 10; i++) {
			insertUser(i);
		}

		Page<User> page = PageHelper.startPage(1,3, true);
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("username", "admin");
		userMapper.selectByExample(example);

		assertTrue(page.size()>0 && page.size()<=3);
	}

}