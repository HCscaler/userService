package com.auth.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

//import com.auth.web.vo.TokenRefreshRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.jwt.JwtTokenProvider;
import com.auth.model.ERole;
import com.auth.model.EmailRequest;
import com.auth.model.Role;
import com.auth.model.User;
import com.auth.service.RefreshTokenService;
import com.auth.service.RoleService;
import com.auth.service.UserService;
import com.auth.web.vo.LoginRequest;
import com.auth.web.vo.SignUpRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String jwt = tokenProvider.generateToken(authentication);

            // Retrieve user details
            Optional<User> optionalUser = userService.findByUsername(loginRequest.getUsername());

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                // Prepare response
                Map<String, Object> response = new HashMap<>();
                response.put("token", jwt);
                response.put("username", user.getUsername());
                response.put("roles", user.getRoles().stream()
                        .map(role -> role.getName().toString()) // Assuming roles are represented as strings
                        .collect(Collectors.toList()));

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }
        } catch (BadCredentialsException e) {
            logger.error("Invalid username or password for user: {}", loginRequest.getUsername(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (AuthenticationException e) {
            logger.error("Authentication error for user: {}", loginRequest.getUsername(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        // Assuming you have a default role for registered users
        Role userRole = roleService.findByName(ERole.ROLE_USER)
                                   .orElseThrow(() -> new RuntimeException("Default role not found"));

        // Create user with role
        User createdUser = userService.createUser(signUpRequest.getUsername(), signUpRequest.getPassword(), Set.of(userRole));
       
        return ResponseEntity.ok("User registered successfully");
    }
    

//    @PostMapping("/refresh-token")
//    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
//        String requestRefreshToken = request.getRefreshToken();
//
//        return refreshTokenService.findByToken(requestRefreshToken)
//                .map(refreshTokenService::verifyExpiration)
//                .map(RefreshToken::getUser)
//                .map(user -> {
//                    String token = tokenProvider.generateToken(user.getUsername());
//                    Map<String, String> response = new HashMap<>();
//                    response.put("token", token);
//                    return ResponseEntity.ok(response);
//                })
//                .orElseThrow(() -> new RuntimeException("Refresh token is invalid or expired!"));
//    }
}
