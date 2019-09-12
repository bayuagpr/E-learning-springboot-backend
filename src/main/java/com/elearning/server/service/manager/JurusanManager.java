package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Jurusan;;
 
public interface JurusanManager {

    List<Jurusan> semuaJurusan();

    Jurusan simpanJurusan(Jurusan ds);
    Jurusan pilihJurusan(String id);
}