//package com.auth.jwt;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JwtAuthenticationProvider {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    public JwtAuthenticationProvider(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    public String authenticate(String username, String password) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            return jwtTokenProvider.generateToken(username);
//        } catch (BadCredentialsException e) {
//            throw new RuntimeException("Invalid username or password", e);
//        } catch (AuthenticationException e) {
//            throw new RuntimeException("Authentication error", e);
//        }
//    }
//}
