
package com.elearning.server.service;

import com.elearning.server.model.User;
import com.elearning.server.repository.RoleRepository;
import com.elearning.server.repository.UserRepository;
import com.elearning.server.service.manager.RegistrationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class RegistrationService implements RegistrationManager {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void simpanUser(User user) {
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