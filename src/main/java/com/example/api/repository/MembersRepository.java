package com.example.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.api.entity.Members;

public interface MembersRepository extends JpaRepository<Members, Long> {
	
	@Query("select m from Members m")
	List<Members> getAll();
	
	@EntityGraph(attributePaths = {"roleSet"},type = EntityGraph.EntityGraphType.LOAD)
	@Query("select m from Members m where m.fromSocial = :social and m.email = :email")
	Optional<Members> findByEmail(String email,boolean social);
	
	@EntityGraph(attributePaths = {"roleSet"},type = EntityGraph.EntityGraphType.LOAD)
	@Query("select m from Members m where m.fromSocial = :social and m.id = :id")
	Optional<Members> findById(String id,boolean social);
}
