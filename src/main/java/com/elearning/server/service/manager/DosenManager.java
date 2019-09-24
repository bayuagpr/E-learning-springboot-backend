package com.elearning.server.service.manager;


import com.elearning.server.model.Dosen;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
 
public interface DosenManager {

    Page<Dosen> semuaDosen(Pageable pageable);

    Dosen pilihDosen(String id);

    Dosen simpanDosen(Dosen ds);

    void hapusDosen(String id);
    
}