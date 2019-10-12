package com.elearning.server.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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

  @Column(name = "alamat", nullable = true)
  private String alamat;
  //bi-directional many-to-one association to Enrollment
	@OneToMany(mappedBy="mahasiswa")
	@JsonBackReference(value="enrollMahasiswa")	
	private List<Enrollment> enrollments;

	//bi-directional many-to-one association to Hasil
	@OneToMany(mappedBy="mahasiswa")
	@JsonBackReference(value="hasilMahasiswa")
	private List<Hasil> hasils;

	//bi-directional many-to-one association to Jurusan
	@ManyToOne
	@JoinColumn(name="id_jurusan")
	private Jurusan jurusan;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="nim")
	private User user;
}