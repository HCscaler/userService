package com.auth.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.entity.RestResponse;
import com.auth.model.Role;
import com.auth.model.TaskVo;
import com.auth.model.User;
import com.auth.repository.UserRepository;
import com.auth.service.TaskServiceFeign;
import com.auth.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired 
	private TaskServiceFeign taskServiceFeign;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public KafkaTemplate<String, User> kafkaTemplate;

	public static final String TOPIC = "users";

	public void sendUserToKafka(User user) {
		kafkaTemplate.send("users", user);
		System.out.println("Users :" + user);
	}


	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public User createUser(String username, String fullname, String password, String email, Role role) {
	    User user = new User();
	    user.setUsername(username);
	    user.setFullname(fullname);
	    user.setPassword(passwordEncoder.encode(password));
	    user.setEmail(email);
	    user.setRole(role);  // Set the single role for the user
	   // sendUserToKafka(user);
	    return userRepository.save(user);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<TaskVo> fetchByUserId(Long userId) {
		try {

			ResponseEntity<RestResponse<List<TaskVo>>> tasks = taskServiceFeign.fetchByUserId(userId);
			List<TaskVo> list = tasks.getBody().getData();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Occurs ::::"+e.getMessage());
			return null;
		}
		
	}
}
