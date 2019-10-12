package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Jurusan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;;
 
public interface JurusanManager {

    List<Jurusan> semuaJurusan();

    Jurusan simpanJurusan(Jurusan ds);
    Jurusan pilihJurusan(String id);
    void hapusJurusan(String id);
}