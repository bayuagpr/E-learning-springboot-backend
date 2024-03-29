package com.elearning.server.service;

import java.util.List;

import com.elearning.server.model.Enrollment;
import com.elearning.server.repository.EnrollmentRepository;
import com.elearning.server.service.manager.EnrollmentManager;

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
public class EnrollmentService implements EnrollmentManager{

	private EnrollmentRepository enrollmentRepository;

	@Autowired
	public EnrollmentService(EnrollmentRepository enrollmentRepository) {
		this.enrollmentRepository = enrollmentRepository;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public Page<Enrollment> semuaEnrollment(Pageable paging) {
		return enrollmentRepository.findAll(paging);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Enrollment simpanEnrollment(Enrollment ds) {
		return enrollmentRepository.save(ds);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Enrollment pilihEnrollment(String id) {
		// TODO Auto-generated method stub
		return enrollmentRepository.findById(id).get();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void hapusEnrollment(String id){
		enrollmentRepository.deleteById(id);
	}

	@Override
	public Page<Enrollment> semuaHasilKelas(String id, Boolean disetujui,Pageable paging) {
		// TODO Auto-generated method stub
		return enrollmentRepository.findByKelas(id,disetujui, paging);
	}

	@Override
	public Page<Enrollment> semuaHasilMahasiswa(String id,Boolean disetujui, Pageable paging) {
		// TODO Auto-generated method stub
		return enrollmentRepository.findByMahasiswa(id,disetujui, paging);
	}

	public boolean existMahasiswaKelas(String idMahasiswa,String idKelas) {
		// TODO Auto-generated method stub
		return enrollmentRepository.existsByMahasiswaKelas(idMahasiswa,idKelas);
	}

	public List<Enrollment> findMahasiswaKelas(String idMahasiswa,String idKelas) {
		// TODO Auto-generated method stub
		return enrollmentRepository.findByMahasiswaKelas(idMahasiswa,idKelas);
	}

}
