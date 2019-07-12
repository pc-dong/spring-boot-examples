package cn.dpc.flyway.repository;

import cn.dpc.flyway.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserRepositoryTest extends AbstractIntegrationTest {

	@Autowired
	UserRepository userRepository;

	@Test
	public void testFindAll(){
		assertTrue(userRepository.findAll().iterator().hasNext());

		assertEquals(userRepository.findAll().iterator().next().getUsername(), "admin");
	}
}