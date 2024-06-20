package com.example.api.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Members extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mno;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String id;
	
	@Column(unique = true)
	private String password;
	
	private String state;
	private String name;
	private String mobile;
	private String gender;
	private String zipcode;
	private String address;
	private String imgName;
	private String imgUuid;
	private String imgPath;
	private LocalDate birthday; //생년원일
	private LocalDate stateday; //갱신일
	private boolean fromSocial;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Builder.Default
	private Set<MembersRole> roleSet = new HashSet<>();
	
	public void addMembersRole(MembersRole membersRole) {
		roleSet.add(membersRole);
	}

}
