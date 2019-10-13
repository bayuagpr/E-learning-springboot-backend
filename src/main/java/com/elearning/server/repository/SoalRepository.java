package com.elearning.server.repository;

import com.elearning.server.model.Soal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
* Generated by Spring Data Generator on 11/09/2019
*/
@Repository
public interface SoalRepository extends JpaRepository<Soal, String>, JpaSpecificationExecutor<Soal> {
    @Query(
        value = "SELECT * FROM soal k where k.id_class = :id_class ORDER BY creation_date DESC", 
        nativeQuery=true
    )
    public Page<Soal> findByKelas(@Param("id_class") String idKelas,Pageable paging);
}
