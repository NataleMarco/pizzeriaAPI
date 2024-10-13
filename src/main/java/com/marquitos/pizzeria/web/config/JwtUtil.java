package com.marquitos.pizzeria.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

@Component

public class JwtUtil{

	private static String SECRET_KEY = "f4k1ng_p1zzer14";
	private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
	public String create(String username){
		return JWT.create()
				.withSubject(username)
				.withIssuer("faking-pizza")
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
				.sign(ALGORITHM);
	}

	public boolean isValid(String jwt){
		try{
			JWT.require(ALGORITHM)
					.build()
					.verify(jwt);
			return true;
		}
		catch(JWTVerificationException e) {
			return false;
		}
	}
	public String getUsername(String jwt){
		try {
			return JWT.require(ALGORITHM)
					.build()
					.verify(jwt)
					.getSubject();
		}catch(JWTDecodeException e) {
			System.out.println("Error al intentar decodear el jwt");
			return null ;
		}
	}
}


