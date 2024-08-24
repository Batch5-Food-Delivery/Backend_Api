package com.hostmdy.food.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import com.hostmdy.food.domain.User;
import com.hostmdy.food.domain.UserPrincipal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenProvider {
	private static final long EXPIRATION = 3_600_000;//1hr
	private static final SecretKey SECRET_KEY = Jwts.SIG.HS512.key().build();
	
	public String generateToken(Authentication authentication) {
		
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		User user = userPrincipal.getUser();
		
		Map<String,Object> claims = new HashMap<>();
		claims.put("userId",user.getId());
		claims.put("email",user.getEmail());
		
		Date now = new Date();
		Date expiryDate = new Date(now.getTime()+EXPIRATION);
		
		return Jwts.builder()
				.subject(user.getId().toString())
				.issuedAt(now)
				.expiration(expiryDate)
				.claims(claims)
				.signWith(SECRET_KEY)
				.compact();	
	}
	
	public Boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token);
			log.info("token is validated");
			return true;
			
		} catch (MalformedJwtException e) {
			// TODO: handle exception
			log.error("token is malformed");
		} catch (ExpiredJwtException e) {
			// TODO: handle exception
			log.error("token is expired");
		} catch (UnsupportedJwtException e) {
			// TODO: handle exception
			log.error("token is not supported");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("token validation is failed");
		}
		
		return false;
	}
	
	Long getUserIdFromToken(String token) {
	  Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
	  
	  if(claims == null) {
		  throw new NullPointerException("Claims are null");
	  }
	  
	  return Long.valueOf(claims.get("userId").toString());
	  
	}
	
}
