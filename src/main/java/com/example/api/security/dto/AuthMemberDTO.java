package com.example.api.security.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@ToString
public class AuthMemberDTO extends User implements OAuth2User {
	private String email;
	private Long mno;
	private String password;
	private String name;
	private boolean fromSocial;
	private Map<String, Object> attr;

	public AuthMemberDTO(String username, Long mno, String password, boolean fromSocial,
			Collection<? extends GrantedAuthority> authorities) {

		super(username, password, authorities);
		this.email = username;
		this.mno = mno;
		this.password = password;
		this.fromSocial = fromSocial;
		// TODO Auto-generated constructor stub
	}

	public AuthMemberDTO(String username, Long mno, String password, boolean fromSocial,
			Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
		this(username, mno, password, fromSocial, authorities);

		this.attr = attr;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return this.attr;
	}
	
	

}
