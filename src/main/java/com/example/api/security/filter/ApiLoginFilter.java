package com.example.api.security.filter;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.example.api.security.dto.AuthMemberDTO;
import com.example.api.security.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {

	 private JWTUtil jwtUtil;

	  public ApiLoginFilter(String defaultFilterProcessesUrl, JWTUtil jwtUtil) {
	    super(defaultFilterProcessesUrl);
	    this.jwtUtil = jwtUtil;
	  }

	  @Override
	  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
	    super.successfulAuthentication(request, response, chain, authResult);
	    log.info("ApiLoginFilter..........successful....Authentication.......authResult : " + authResult);
	    log.info("getPrincipal() :  " + authResult.getPrincipal());
	    String email = ((AuthMemberDTO)(authResult.getPrincipal())).getUsername();
	    String token = null;
	    try{
	      token = jwtUtil.generateToken(email);
	      response.setContentType("text/plain");
	      response.getOutputStream().write(token.getBytes());
	      log.info("token:" + token);
	    }catch (Exception e){
	      e.printStackTrace();
	    }
	  }

	  @Override
	  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
	    log.info("ApiLoginFilter..........");
	    String email = request.getParameter("email");
	    String pass = request.getParameter("pass"); // 비밀번호를 그대로 가져옵니다.
	    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, pass);
	    log.info("authToken : " + authToken);
	    return getAuthenticationManager().authenticate(authToken);
	  }
	  @Bean
	  public ApiLoginFilter apiLoginFilter(AuthenticationConfiguration ac) throws Exception{
	    ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/api/login",jwtUtil);
	    apiLoginFilter.setAuthenticationManager(ac.getAuthenticationManager());
	    return apiLoginFilter;
	  }

}
