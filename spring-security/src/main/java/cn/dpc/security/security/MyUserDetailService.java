package cn.dpc.security.security;


import cn.dpc.security.model.User;
import cn.dpc.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getByUsername(username);
		if (!(user.getPassword().startsWith("{") && user.getPassword().contains("}"))) {
			user.setPassword("{noop}" + user.getPassword());
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(role("ADMIN"));
		authorities.add(authority("scan"));
		user.setAuthorities(authorities);
		return user;
	}

	private SimpleGrantedAuthority role(String role) {
		return new SimpleGrantedAuthority("ROLE_" + role);
	}

	private SimpleGrantedAuthority authority(String authority) {
		return new SimpleGrantedAuthority(authority);
	}
}
