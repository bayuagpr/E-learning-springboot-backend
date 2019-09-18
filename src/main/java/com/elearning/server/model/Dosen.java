package com.elearning.server.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import java.io.Serializable;

@Data
@Entity(name = "com.elearning.server.model.Dosen")
@Table(name = "dosen")
public class Dosen implements Serializable{
	private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "nidn", nullable = false)
	private String nidn;

  @Column(name = "alamat", nullable = true)
  private String alamat;

  @Column(name = "nama", nullable = true)
  private String nama;

	@Temporal(TemporalType.DATE)
	@Column(name="`tanggal lahir`")
	private Date tanggal_lahir;

	@Column(name="`tempat lahir`")
	private String tempat_lahir;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="nidn")
	@JsonIgnore
	private User user;

	//bi-directional many-to-one association to Kelas
	@OneToMany(mappedBy="dosen")
  private List<Kelas> kelas;



}