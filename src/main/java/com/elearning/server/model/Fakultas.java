package com.elearning.server.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import lombok.Data;

@Data
@Entity(name = "com.elearning.server.model.Fakultas")
@Table(name = "fakultas")
public class Fakultas implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", nullable = false)
  private String id;
  @Column(name = "nama", nullable = true)
  private String nama;
  @Column(name = "deskripsi", nullable = true)
  private String deskripsi;
  //bi-directional many-to-one association to Jurusan
  @OneToMany(mappedBy="fakultas")
  @JsonBackReference(value="jurusanFakultas")
	private List<Jurusan> jurusans;
}