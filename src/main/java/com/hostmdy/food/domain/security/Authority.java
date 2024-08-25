package com.hostmdy.food.domain.security;

import org.springframework.security.core.GrantedAuthority;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Authority implements GrantedAuthority {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String authorized;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return authorized;
	}

}
