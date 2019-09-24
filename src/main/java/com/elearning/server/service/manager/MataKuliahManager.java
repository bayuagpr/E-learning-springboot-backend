package com.elearning.server.service.manager;

import java.util.List;
import java.util.Optional;

import com.elearning.server.model.MataKuliah;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;;
 
public interface MataKuliahManager {

    Page<MataKuliah> semuaMataKuliah(Pageable paging);

    MataKuliah simpanMataKuliah(MataKuliah ds);
    MataKuliah pilihMataKuliah(String id);
    void hapusMataKuliah(String id);
}