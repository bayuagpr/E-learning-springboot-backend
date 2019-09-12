package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.Materi;
 
public interface MateriManager {

    List<Materi> semuaMateri();

    Materi simpanMateri(Materi ds);
    Materi pilihMateri(String id);
}