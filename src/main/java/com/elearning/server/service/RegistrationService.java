
package com.elearning.server.service;

import com.elearning.server.model.User;
import com.elearning.server.repository.RoleRepository;
import com.elearning.server.repository.UserRepository;
import com.elearning.server.service.manager.RegistrationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;

@Service
@Slf4j
public class RegistrationService implements RegistrationManager {
  
    private UserRepository userRepository;
  
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public RegistrationService(UserRepository userRepository,PasswordEncoder passwordEncoder ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void simpanUser(User user) {
        log.info(user.getPassword());
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole());
        userRepository.save(user);
    }

    @Override
    public boolean cariKetersediaanEmail(String email) {
        return userRepository.findByEmail(email) == null;
    }
}