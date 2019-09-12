package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Soal;
 
public interface SoalManager {

    List<Soal> semuaSoal();

    Soal simpanSoal(Soal ds);
    Soal pilihSoal(String id);
}