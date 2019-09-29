package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Kelas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;;
 
public interface KelasManager {

    Page<Kelas> semuaKelas(Pageable paging);
    List<Kelas> semuaKelasLain();
    Kelas simpanKelas(Kelas ds);
    Kelas pilihKelas(String id);
    Kelas cariKelas(String namaKelas);
    void hapusKelas(String id);
    List<Kelas> cariKelasSeperti(String nama);
}