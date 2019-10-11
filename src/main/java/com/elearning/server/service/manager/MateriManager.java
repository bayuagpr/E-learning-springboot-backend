package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Materi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
 
public interface MateriManager {

    Page<Materi> semuaMateri(Pageable paging);

    Materi simpanMateri(Materi ds);
    Materi pilihMateri(String id);
    void hapusMateri(String id);
    Page<Materi> semuaMateriKelas(String id, Pageable paging);
}