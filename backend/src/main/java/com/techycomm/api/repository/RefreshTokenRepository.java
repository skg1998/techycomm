package com.techycomm.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techycomm.api.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
