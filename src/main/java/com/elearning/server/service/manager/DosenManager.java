package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Dosen;
 
public interface DosenManager {

    List<Dosen> semuaDosen();

    Dosen pilihDosen(String id);

    Dosen simpanDosen(Dosen ds);
    
}