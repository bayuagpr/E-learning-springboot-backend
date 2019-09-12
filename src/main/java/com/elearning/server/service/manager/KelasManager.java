package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Kelas;;
 
public interface KelasManager {

    List<Kelas> semuaKelas();

    Kelas simpanKelas(Kelas ds);
    Kelas pilihKelas(String id);
}