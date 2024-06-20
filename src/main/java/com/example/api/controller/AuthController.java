package com.example.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {
	
	private final MembersService membersService;
	private final JWTUtil jwtUtil;
	
	@PostMapping(value= "/join")
	public ResponseEntity<Long> register(@RequestBody MembersDTO membersDTO){
		log.info("register...." + membersDTO);
		
		long num = membersService.registMembersDTO(membersDTO);
		return new ResponseEntity<>(num,HttpStatus.OK);
	}

}
