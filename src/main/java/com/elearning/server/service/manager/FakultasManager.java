package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Fakultas;;
 
public interface FakultasManager {

    List<Fakultas> semuaFakultas();

    Fakultas simpanFakultas(Fakultas ds);
    
    Fakultas pilihFakultas(String id);
}