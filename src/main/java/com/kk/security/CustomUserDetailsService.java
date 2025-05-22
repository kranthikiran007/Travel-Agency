package com.kk.security;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.kk.controller.CustomerController;
import com.kk.entity.User;
import com.kk.repository.UserDetaillsRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{


	@Autowired
	private UserDetaillsRepository detaillsRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user= detaillsRepository.findByUsernameOrEmail(username, username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
		List<GrantedAuthority> authorities= user.getRoles().stream().map(t -> new SimpleGrantedAuthority(t.getName())).collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPwd(),authorities);
	}
	

}
