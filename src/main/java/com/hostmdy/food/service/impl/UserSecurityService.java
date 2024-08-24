package com.hostmdy.food.service.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hostmdy.food.domain.User;
import com.hostmdy.food.domain.UserPrincipal;
import com.hostmdy.food.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSecurityService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userOptional = userRepository.findByUsername(username);
		if (userOptional.isEmpty()) {
			throw new UsernameNotFoundException("username is not found");
		}
		
		return new UserPrincipal(userOptional.get());
		
	}
	
	public User loadUserById(Long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(userOptional.isEmpty()) {
			log.info("userId = {} is not found",userId);
			
			throw new UsernameNotFoundException("user with id="+userId+" is not found");
		}
		
		return userOptional.get();
	}
	
}
