package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Hasil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
 
public interface HasilManager {

    Page<Hasil> semuaHasil(Pageable paging);
    Page<Hasil> semuaHasilSoal(String id, String status, Pageable paging);
    Page<Hasil> semuaHasilMahasiswa(String id, Pageable paging);
    Hasil simpanHasil(Hasil ds);
    Hasil pilihHasil(String id);
    void hapusHasil(String id);
}