package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Mahasiswa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
 
public interface MahasiswaManager {

    Page<Mahasiswa> semuaMahasiswa(Pageable paging);

    Mahasiswa simpanMahasiswa(Mahasiswa ds);
    Mahasiswa pilihMahasiswa(String id);
    void hapusMahasiswa(String id);
}