package com.auth.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.model.User;
import com.auth.service.UserService;

@RestController
@RequestMapping("/api/auth/users")
public class UserController {
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> userOptional = userService.findById(id);
		if (userOptional.isPresent()) {
			return ResponseEntity.ok(userOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> updateUserById(@PathVariable Long id, @Valid @RequestBody User userDetails) {
		Optional<User> userOptional = userService.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setUsername(userDetails.getUsername());
			user.setFullname(userDetails.getFullname());
			user.setPassword(encoder.encode(userDetails.getPassword()));
			System.out.println("Password: " + user.getPassword());
			user.setEmail(userDetails.getEmail());
			user.setRole(userDetails.getRole());
			User updatedUser = userService.save(user);
			return ResponseEntity.ok(updatedUser);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllUsers() {
		return userService.findAll();
	}

//	@GetMapping("/task/userId")
//	public ResponseEntity<Object> getTaskByUserId(@RequestParam Long id) {
//
//		System.out.println(" data is :" );
//		return new ResponseEntity<>(userService.fetchByUserId(id),HttpStatus.OK);
//	}
}
