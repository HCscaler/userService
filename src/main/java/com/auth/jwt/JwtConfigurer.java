//package com.auth.jwt;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//
//    private final JwtTokenProvider tokenProvider;
//    private final UserDetailsService userDetailsService;
//
//    @Autowired
//    public JwtConfigurer(JwtTokenProvider tokenProvider, UserDetailsService userDetailsService) {
//        this.tokenProvider = tokenProvider;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        JwtTokenFilter customFilter = new JwtTokenFilter(tokenProvider, userDetailsService);
//        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}
