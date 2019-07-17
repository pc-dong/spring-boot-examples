package cn.dpc.security;


import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

public class PasswordEncoderUnitTest {

	@Test
	public void testPasswordEncoder(){
		System.out.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("admin"));
	}
}
