package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Enrollment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
 
public interface EnrollmentManager {

    Page<Enrollment> semuaEnrollment(Pageable paging);
    Page<Enrollment> semuaHasilKelas(String id, Boolean disetujui,Pageable paging);
    Page<Enrollment> semuaHasilMahasiswa(String id, Pageable paging);
    Enrollment simpanEnrollment(Enrollment ds);
    
    Enrollment pilihEnrollment(String id);   
     
    void hapusEnrollment(String id);
}