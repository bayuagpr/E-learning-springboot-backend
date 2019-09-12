package com.elearning.server.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;;

@Data
@Entity(name = "com.elearning.server.model.Kelas")
@Table(name = "kelas")
public class Kelas implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "id", nullable = false)
  private String id;
  @Column(name = "nama", nullable = true)
  private String nama;

  //bi-directional many-to-one association to Enrollment
	@OneToMany(mappedBy="kelas")
	private List<Enrollment> enrollments;

	//bi-directional many-to-one association to Dosen
	@ManyToOne
	@JoinColumn(name="id_dosen")
	private Dosen dosen;

	//bi-directional many-to-one association to MataKuliah
	@ManyToOne
	@JoinColumn(name="id_matkul")
	private MataKuliah mataKuliah;

	//bi-directional many-to-one association to Materi
	@OneToMany(mappedBy="kelas")
	private List<Materi> materis;

	//bi-directional many-to-one association to Soal
	@OneToMany(mappedBy="kelas")
	private List<Soal> soals;
}