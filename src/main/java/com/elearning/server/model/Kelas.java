package com.elearning.server.model;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import java.io.Serializable;
import java.util.List;;

@Data
@Entity(name = "com.elearning.server.model.Kelas")
@Table(name = "kelas")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Kelas.class)
public class Kelas implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "id", nullable = false)
  private String id;
  @Column(name = "nama", nullable = true)
  private String nama;

  //bi-directional many-to-one association to Enrollment
	@OneToMany(mappedBy="kelas")
	@JsonBackReference(value="enrollKelas")
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
	@JsonBackReference(value="materiKelas")
	private List<Materi> materis;

	//bi-directional many-to-one association to Soal
	@OneToMany(mappedBy="kelas")
	@JsonBackReference(value="soalKelas")
	private List<Soal> soals;
}