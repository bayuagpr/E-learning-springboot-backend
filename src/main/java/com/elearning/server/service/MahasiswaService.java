package com.elearning.server.service;

import java.util.List;

import com.elearning.server.model.Mahasiswa;
import com.elearning.server.repository.MahasiswaRepository;
import com.elearning.server.service.manager.MahasiswaManager;

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
public class MahasiswaService implements MahasiswaManager{

	private MahasiswaRepository mahasiswaRepository;

	@Autowired
	public MahasiswaService(MahasiswaRepository mahasiswaRepository) {
		this.mahasiswaRepository = mahasiswaRepository;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public Page<Mahasiswa> semuaMahasiswa(Pageable paging) {
		return mahasiswaRepository.findAll(paging);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Mahasiswa simpanMahasiswa(Mahasiswa ds) {
		return mahasiswaRepository.save(ds);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Mahasiswa pilihMahasiswa(String id) {
		// TODO Auto-generated method stub
		return mahasiswaRepository.findById(id).get();
	}

	public void hapusMahasiswa(String id){
		mahasiswaRepository.deleteById(id);
	}
}
