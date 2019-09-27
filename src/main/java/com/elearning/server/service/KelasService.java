package com.elearning.server.service;

import java.util.List;

import com.elearning.server.model.Kelas;
import com.elearning.server.repository.KelasRepository;
import com.elearning.server.service.manager.KelasManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
* Generated by Spring Data Generator on 11/09/2019
*/
@Service
public class KelasService implements KelasManager{

	private KelasRepository kelasRepository;

	@Autowired
	public KelasService(KelasRepository kelasRepository) {
		this.kelasRepository = kelasRepository;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public Page<Kelas> semuaKelas(Pageable paging) {
		return kelasRepository.findAll(paging);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public List<Kelas> semuaKelasLain() {
		return kelasRepository.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Kelas simpanKelas(Kelas ds) {
		return kelasRepository.save(ds);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Kelas pilihKelas(String id) {
		// TODO Auto-generated method stub
		return kelasRepository.findById(id).get();
	}

	@Override
	public Kelas cariKelas(String namaKelas) {
		// TODO Auto-generated method stub
		return kelasRepository.findByNama(namaKelas);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void hapusKelas(String id) {
		// TODO Auto-generated method stub
		kelasRepository.deleteById(id);
	}
}
