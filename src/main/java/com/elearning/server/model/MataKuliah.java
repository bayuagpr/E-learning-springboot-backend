package com.elearning.server.model;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import lombok.Data;
import java.util.List;

@Data
@Entity(name = "com.elearning.server.model.MataKuliah")
@Table(name = "mata_kuliah")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = MataKuliah.class)
public class MataKuliah implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", nullable = false)
  private String id;
  @Column(name = "nama", nullable = true)
  private String nama;
  @Column(name = "deskripsi", nullable = true)
  private String deskripsi;
  @Column(name = "tipe", nullable = true)
  @Enumerated(EnumType.STRING)
  private TipeMataKuliah tipe;
  @Column(name = "term", nullable = true)
  @Enumerated(EnumType.STRING)
  private TermMataKuliah term;
  //bi-directional many-to-one association to Kela
  @OneToMany(mappedBy="mataKuliah")
  @JsonBackReference(value="matkulKelas")
	private List<Kelas> kelas;
}