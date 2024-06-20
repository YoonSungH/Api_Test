package com.example.api.service;

import java.util.stream.Collectors;

import com.example.api.dto.MembersDTO;
import com.example.api.entity.Members;
import com.example.api.entity.MembersRole;

public interface MembersService{
	default Members dtoToEntity(MembersDTO membersDTO) {
		Members members = Members.builder()
				.mno(membersDTO.getMno())
				.id(membersDTO.getId())
				.name(membersDTO.getName())
				.password(membersDTO.getPassword())
				.gender(membersDTO.getGender())
				.email(membersDTO.getEmail())
				.mobile(membersDTO.getMobile())
				.imgName(membersDTO.getImgName())
			    .imgUuid(membersDTO.getImgUuid())
			    .imgPath(membersDTO.getImgPath())
			    .birthday(membersDTO.getBirthday())
			    .fromSocial(membersDTO.isFromSocial())
			    .roleSet(membersDTO.getRoleSet().stream().map(str -> {
			    	if(str.equals("ROLE_USER")) return MembersRole.USER;
			    	else if(str.equals("ROLE_MANAGER")) return MembersRole.MANAGER;
			    	else if(str.equals("ROLE_ADMIN")) return MembersRole.ADMIN;
			    	else return MembersRole.USER;
			    }).collect(Collectors.toSet()))
			    .build();
		return members;
	}

}
