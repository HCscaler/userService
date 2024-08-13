package com.auth.service;

import java.util.List;
import java.util.Optional;

import com.auth.model.Role;
import com.auth.model.TaskVo;
import com.auth.model.User;


public interface UserService {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    User createUser(String username, String fullname, String password,String email, Role roles);
    Optional<User> findById(Long id);
    User save(User user);
	List<User> findAll();
	public List<TaskVo> fetchByUserId(Long userId);
}
