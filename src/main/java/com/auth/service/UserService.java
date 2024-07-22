package com.auth.service;

import com.auth.model.Role;
import com.auth.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    User createUser(String username, String password, Set<Role> roles);
    Optional<User> findById(Long id);
    User save(User user);
	List<User> findAll();
}
