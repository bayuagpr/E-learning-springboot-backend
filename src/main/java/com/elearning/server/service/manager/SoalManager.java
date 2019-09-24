package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Soal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
 
public interface SoalManager {

    Page<Soal> semuaSoal(Pageable paging);

    Soal simpanSoal(Soal ds);
    Soal pilihSoal(String id);
    void hapusSoal(String id);
}