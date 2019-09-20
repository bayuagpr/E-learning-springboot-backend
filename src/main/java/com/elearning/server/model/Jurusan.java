package com.elearning.server.model;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "com.elearning.server.model.Jurusan")
@Table(name = "jurusan")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Jurusan.class)
public class Jurusan implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", nullable = false)
  private String id;
  @Column(name = "nama", nullable = true)
  private String nama;
  //bi-directional many-to-one association to Fakulta
	@ManyToOne
  @JoinColumn(name="id_fakultas")
	private Fakultas fakultas;

	//bi-directional many-to-one association to Mahasiswa
  @OneToMany(mappedBy="jurusan")
  @JsonBackReference(value="mahasiswaJurusan")
	private List<Mahasiswa> mahasiswas;

}