package com.biere.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.biere.entities.User;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserById(username);

		if (user.getUsername().equals(username)) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	public User save(User user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setFirstname(user.getFirstname());
		newUser.setLastname(user.getLastname());
		newUser.setPhone(user.getPhone());
		return userService.createOrUpdate(newUser);
	}
}