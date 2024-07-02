package com.example.api.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.api.security.util.JWTUtil;
import com.example.api.dto.MembersDTO;
import com.example.api.repository.MembersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MembersServiceImpl implements MembersService {
    private final MembersRepository membersRepository;
    private final PasswordEncoder passwordEncoder;
	@Override
	public Long registMembersDTO(MembersDTO membersDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateMembersDTO(MembersDTO membersDTO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeMembers(Long num) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public MembersDTO get(Long num) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<MembersDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String login(String email, String pass, JWTUtil jwtUtil) {
		// TODO Auto-generated method stub
		return null;
	}

    
}
