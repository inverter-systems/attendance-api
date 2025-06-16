package com.inverter.auth;

import static com.inverter.auth.config.SecurityConfiguration.getEndpointsWithAutenticationNotReuired;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.inverter.auth.service.MessageService;
import com.inverter.auth.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter {

	private TokenService tokenService;
	private MessageService msg;

	public FilterToken(TokenService tokenService, MessageService msg) {
		this.tokenService = tokenService;
		this.msg = msg;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
	        throws ServletException, IOException {
		var path = req.getRequestURI();
		
		if (getEndpointsWithAutenticationNotReuired().anyMatch(str -> str.equalsIgnoreCase(path))) {
			chain.doFilter(req, res);
			return;
		}

		final String ERROR_LABEL_ATTRIBUTE = "error";
	    final String TOKEN_EXPIRED_ERROR_MSG = msg.get("user.auth.token.error.expired");
	    final String TOKEN_DECODE_ERROR_MSG = msg.get("user.auth.token.error.decode");
	    final String TOKEN_SIGNATUE_ERROR_MSG = msg.get("user.auth.token.error.signature");

	    try {
	       
	    	tokenService.authenticateRequest(req);

	    } catch (TokenExpiredException e) {
	        handleTokenException(req, ERROR_LABEL_ATTRIBUTE, String.format(TOKEN_EXPIRED_ERROR_MSG, e.getExpiredOn()), e);
	    } catch (JWTDecodeException e) {
	        handleTokenException(req, ERROR_LABEL_ATTRIBUTE, TOKEN_DECODE_ERROR_MSG, e);
	    } catch (SignatureVerificationException e) {
	        handleTokenException(req, ERROR_LABEL_ATTRIBUTE, TOKEN_SIGNATUE_ERROR_MSG, e);
	    } catch (Exception e) {
	        handleTokenException(req, ERROR_LABEL_ATTRIBUTE, e.getMessage(), e);
	    }

	    chain.doFilter(req, res);
	}

	private void handleTokenException(HttpServletRequest req, String attr, String message, Exception e) throws ServletException {
	    req.setAttribute(attr, message);
	    throw new ServletException(e.getMessage(), e);
	}
}