
package com.socialcb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SigninController {

	@Autowired
	@Qualifier("authenticationManager")
	AuthenticationManager authManager;

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signin() {
		return "signin";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signinAuthenticate(HttpServletRequest request,
			HttpServletResponse response) {

		String username = request.getParameter("j_username");
		String password = request.getParameter("j_password");

		try {
			Authentication token = new UsernamePasswordAuthenticationToken(
					username, password);
			Authentication authentication = authManager.authenticate(token);

			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
			return "twitterConnect";
		} catch (AuthenticationException e) {
			return "error";
		}

	}
}
