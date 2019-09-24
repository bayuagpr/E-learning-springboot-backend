package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Fakultas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;;
 
public interface FakultasManager {

    Page<Fakultas> semuaFakultas(Pageable paging);

    Fakultas simpanFakultas(Fakultas ds);
    
    Fakultas pilihFakultas(String id);

    void hapusFakultas(String id);
}