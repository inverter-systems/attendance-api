package com.inverter.auth.service.impl;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.inverter.auth.entity.User;
import com.inverter.auth.enums.IssueEnum;
import com.inverter.auth.repository.UserRepository;
import com.inverter.auth.service.TokenService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenServiceImpl implements TokenService {
	
	private UserRepository repo;
	
	@Value("${jwt.expiration.user.min}")
	private Integer expirationUser;
	
	@Value("${jwt.expiration.user.activation.min}")
	private Integer expirationActivation;
	
	@Value("${jwt.expiration.user.reset.password.min}")
	private Integer expirationResetPassword;
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${zone.off.set}")
	private String zoneOffSet;
	
    public TokenServiceImpl(UserRepository repo) {
		this.repo = repo;
	}

	public String buildUserToken(User usuario) {
    	var expiration = ZonedDateTime.now(ZoneId.of(zoneOffSet)).plusMinutes(expirationUser).toInstant();
        return JWT.create()
                .withIssuer(IssueEnum.ISSUE.getDesc())
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(expiration)
                .sign(Algorithm.HMAC256(secret));
    }
	
    public String buildActivationToken(User usuario) {
    	var expiration = ZonedDateTime.now(ZoneId.of(zoneOffSet)).plusMinutes(expirationActivation).toInstant();
        return JWT.create()
                .withIssuer(IssueEnum.ISSUE_ACTIVATION.getDesc())
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(expiration)
                .sign(Algorithm.HMAC256(secret));
    }
    
    public String buildPasswordResetToken(User usuario) {
    	var expiration = ZonedDateTime.now(ZoneId.of(zoneOffSet)).plusMinutes(expirationResetPassword).toInstant();
        return JWT.create()
                .withIssuer(IssueEnum.ISSUE_RESET_PASSWORD.getDesc())
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(expiration)
                .sign(Algorithm.HMAC256(secret));
    }

    public String getSubject(String token, IssueEnum issue) {
        return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(issue.getDesc())
                .build().verify(token).getSubject();

    }
	
	public Instant getExpires(String token, IssueEnum issue) {
		return JWT.require(Algorithm.HMAC256(secret))
				.withIssuer(issue.getDesc())
				.build().verify(token).getExpiresAtAsInstant();
	}
	
	public String extractTokenFromCookies(Cookie[] cookies) {
	    if (cookies == null) return "";
	    return Arrays.stream(cookies)
	            .filter(c -> "access_token".equals(c.getName()))
	            .map(Cookie::getValue)
	            .findFirst()
	            .orElse("");
	}
	
	public User authenticateRequest(HttpServletRequest req) {
		String token = extractTokenFromCookies(req.getCookies());
		Optional<User> user = Optional.empty();
				
		if (!token.isEmpty()) {
			String subject = getSubject(token, IssueEnum.ISSUE);
			user = repo.findByUsername(subject);
		}	

	    return user.map(u -> {
	        var authentication = new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        req.setAttribute("authentication", "authenticated");
	        return u;
	    }).orElse(null);
	}

	
}