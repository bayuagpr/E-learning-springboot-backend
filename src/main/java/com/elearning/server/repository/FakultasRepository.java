package com.elearning.server.repository;

import com.elearning.server.model.Fakultas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
* Generated by Spring Data Generator on 11/09/2019
*/
@Repository
public interface FakultasRepository extends JpaRepository<Fakultas, String>, JpaSpecificationExecutor<Fakultas> {

}
