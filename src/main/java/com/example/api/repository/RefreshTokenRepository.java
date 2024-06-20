package com.example.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.example.api.entity.RefreshToken;

import jakarta.transaction.Transactional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByUserId(Long userId);
	Optional<RefreshToken> findByRefreshToken(String refreshToken);
	
	@Modifying
	@Transactional
	void deleteByUserId(Long mno);

}
