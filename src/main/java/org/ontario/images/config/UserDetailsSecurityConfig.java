package org.ontario.images.config;

import org.ontario.images.repositories.UserRepository;
import org.ontario.images.services.security.DomainUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserDetailsSecurityConfig {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsSecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new DomainUserDetailsService(userRepository);
    }
}
