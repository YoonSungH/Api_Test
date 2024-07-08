package com.example.api.security.filter;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.server.PathContainer;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import com.example.api.security.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {
	private String[] pattern;
	private JWTUtil jwtUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("REQUEST URI: " + request.getRequestURI());
		boolean check = false;
		for (int i = 0; i< pattern.length;i++) {
			boolean matches = false;
			String patternPath = request.getContextPath() + pattern[i];
			String requestPath = request.getRequestURI();
			log.info("request.getContextPath() + pattern[i] : " + patternPath);
			log.info("request.getRequestURI(): " + requestPath);
			
			PathPatternParser pathPatternParser = new PathPatternParser();
			String tmp = antpathToPathpattern2(patternPath);
			log.info("tmp: " + tmp);
			PathPattern pattern = pathPatternParser.parse(tmp.trim());
			PathContainer parsePath = PathContainer.parsePath(requestPath);
			matches = pattern.matches(parsePath);
			log.info("matches: " + matches);
			
			if(matches) {
				check = true;
				break;
			}
		}
		if(check) {
			boolean checkHeader = checkAuthHeader(request);
			log.info("ApiCheckFilter........checkHeader : " + checkHeader);
			if(checkHeader) {
				filterChain.doFilter(request, response);
			}else {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				response.setContentType("application/json;charset=utf-8");
				JSONObject jsonObject = new JSONObject();
				String message = "FAIL CHECK API TOKEN";
				jsonObject.put("code","403" );
				jsonObject.put("message",message);
				PrintWriter printWriter = response.getWriter();
				printWriter.print(jsonObject);
			}
			return;
		}
		filterChain.doFilter(request, response);
	}
	
	private boolean checkAuthHeader(HttpServletRequest request) {
		boolean checkResult = false;
		String authHeader = request.getHeader("Authorization");
		if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")) {
			log.info("Authorization exist: " + authHeader);
			try {
				String email = jwtUtil.validateAndExtract(authHeader.substring(7));
				log.info("validate result: " + email);
				checkResult = email.length() > 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return checkResult;
	}
	
	private String antpathToPathpattern2(String str) {
		str = str.replace("**","{*path}");
		log.info("antpathToPathpattern2: " + str);
		return str;
	}
}
