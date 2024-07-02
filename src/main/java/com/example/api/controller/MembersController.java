package com.example.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.api.dto.MembersDTO;
import com.example.api.service.MembersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/members/")
@RequiredArgsConstructor
public class MembersController {
	private final MembersService membersService;
	
	@PutMapping(value ="/update",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> update(@RequestBody MembersDTO membersDTO){
		log.info("update..."+membersDTO);
		
		membersService.updateMembersDTO(membersDTO);
		return new ResponseEntity<>("modified",HttpStatus.OK);
	}

}
