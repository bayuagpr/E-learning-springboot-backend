package com.elearning.server.service;

import java.util.List;

import com.elearning.server.model.Fakultas;
import com.elearning.server.repository.FakultasRepository;
import com.elearning.server.service.manager.FakultasManager;

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
public class FakultasService implements FakultasManager{

	private FakultasRepository fakultasRepository;

	@Autowired
	public FakultasService(FakultasRepository fakultasRepository) {
		this.fakultasRepository = fakultasRepository;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public Page<Fakultas> semuaFakultas(Pageable paging) {
		return fakultasRepository.findAll(paging);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Fakultas simpanFakultas(Fakultas ds) {
		return fakultasRepository.save(ds);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Fakultas pilihFakultas(String id) {
		// TODO Auto-generated method stub
		return fakultasRepository.findById(id).get();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void hapusFakultas(String id){
		fakultasRepository.deleteById(id);
	}

}
