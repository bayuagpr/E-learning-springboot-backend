package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Hasil;
 
public interface HasilManager {

    List<Hasil> semuaHasil();

    Hasil simpanHasil(Hasil ds);
    Hasil pilihHasil(String id);
}