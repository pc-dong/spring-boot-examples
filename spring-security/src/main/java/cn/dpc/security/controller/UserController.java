package cn.dpc.security.controller;


import cn.dpc.security.model.User;
import cn.dpc.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PreAuthorize("hasRole('ADMIN') or hasAuthority('scan')")
	@GetMapping
	public UserDetails getCurrentUser() {
		return  (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{username}")
	public User getByName(@PathVariable("username") String username) {
		return userRepository.getByUsername(username);
	}
}
