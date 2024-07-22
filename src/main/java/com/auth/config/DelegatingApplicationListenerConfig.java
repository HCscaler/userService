//package com.auth.config;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DelegatingApplicationListenerConfig {
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @PostConstruct
//    public void initialize() {
//        // Initialization logic if needed
//        System.out.println("DelegatingApplicationListenerConfig initialized.");
//    }
//
//    // Avoid complex operations or requesting other beans in @PreDestroy
//    @PreDestroy
//    public void destroy() {
//        // Clean-up logic, but avoid accessing other beans to prevent BeanCreationNotAllowedException
//        System.out.println("DelegatingApplicationListenerConfig destroyed.");
//    }
//}
