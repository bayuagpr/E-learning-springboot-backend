package com.elearning.server.service.manager;

import java.util.List;

import com.elearning.server.model.MataKuliah;;
 
public interface MataKuliahManager {

    List<MataKuliah> semuaMataKuliah();

    MataKuliah simpanMataKuliah(MataKuliah ds);
    MataKuliah pilihMataKuliah(String id);
}