package com.hostmdy.food.payload;

import java.util.List;

import com.hostmdy.food.domain.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public class LoginResponse {
	private final String token;
	private final User user;
	private final List<String> roles;
	private final Boolean loginStatus;
}
