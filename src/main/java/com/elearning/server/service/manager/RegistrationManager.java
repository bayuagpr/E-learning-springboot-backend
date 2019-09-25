package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.User;
public interface RegistrationManager {

    void simpanUser(User user);
    boolean cariKetersediaanEmail(String email);
}