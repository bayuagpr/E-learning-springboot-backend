package com.elearning.server.model;

import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;
import lombok.Data;
import java.util.List;

@Data
@Entity(name = "com.elearning.server.model.MataKuliah")
@Table(name = "mata_kuliah")
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
  private String tipe;
  @Column(name = "term", nullable = true)
  private String term;
  //bi-directional many-to-one association to Kela
	@OneToMany(mappedBy="mataKuliah")
	private List<Kelas> kelas;
}