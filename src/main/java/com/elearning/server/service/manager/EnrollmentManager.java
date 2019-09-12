package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Enrollment;
 
public interface EnrollmentManager {

    List<Enrollment> semuaEnrollment();

    Enrollment simpanEnrollment(Enrollment ds);
    
    Enrollment pilihEnrollment(String id);    
}