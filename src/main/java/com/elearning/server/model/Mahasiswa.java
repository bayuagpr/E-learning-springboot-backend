package com.elearning.server.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import lombok.Data;

@Data
@Entity(name = "com.elearning.server.model.Mahasiswa")
@Table(name = "mahasiswa")
public class Mahasiswa implements Serializable{


  @Id
  @Column(name = "nim", nullable = false)
  private String nim;
  @Column(name = "nama", nullable = true)
  private String nama;
  @Temporal(TemporalType.DATE)
	@Column(name="tanggal_lahir")
	private Date tanggal_lahir;

	@Column(name="tempat_lahir")
  private String tempat_lahir;
  //bi-directional many-to-one association to Enrollment
	@OneToMany(mappedBy="mahasiswa")
	private List<Enrollment> enrollments;

	//bi-directional many-to-one association to Hasil
	@OneToMany(mappedBy="mahasiswa")
	private List<Hasil> hasils;

	//bi-directional many-to-one association to Jurusan
	@ManyToOne
	@JoinColumn(name="id_jurusan")
	private Jurusan jurusan;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="nim")
	@JsonIgnore
	private User user;
}