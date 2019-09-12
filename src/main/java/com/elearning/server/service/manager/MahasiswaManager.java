package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Mahasiswa;
 
public interface MahasiswaManager {

    List<Mahasiswa> semuaMahasiswa();

    Mahasiswa simpanMahasiswa(Mahasiswa ds);
    Mahasiswa pilihMahasiswa(String id);
}