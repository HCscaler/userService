package com.auth.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	 public List<String> getRoles(UserDetails userDetails) {
	        return userDetails.getAuthorities().stream()
	            .map(grantedAuthority -> grantedAuthority.getAuthority())
	            .collect(Collectors.toList());
	    }
}
