package com.auth.service;

import com.auth.model.RefreshToken;
import com.auth.model.User;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);
    RefreshToken createRefreshToken(User user);
    RefreshToken verifyExpiration(RefreshToken token);
    void deleteByUserId(Long userId);
}
