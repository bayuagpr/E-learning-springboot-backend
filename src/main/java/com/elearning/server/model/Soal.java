package com.elearning.server.model;

import java.util.Date;
import java.sql.*;
import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;
import java.util.List;

@Data
@Entity(name = "com.elearning.server.model.Soal")
@Table(name = "soal")
@EntityListeners(AuditingEntityListener.class)
public class Soal implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", nullable = false)
  private String id;
  @Column(name = "judul", nullable = true)
  private String judul;
  @Column(name = "deskripsi", nullable = true)
  private String deskripsi;
  @Column(name = "attachment", nullable = true)
  private String attachment;
  @Column(name = "tipe", nullable = true)
  @Enumerated(EnumType.STRING)
  private TipeSoal tipe;
  @Column(name = "due_date", nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date dueDate;
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "creation_date", nullable = false)
  protected Date creationDate;
//bi-directional many-to-one association to Hasil
@OneToMany(mappedBy="soal")
@JsonBackReference(value="hasilSoal")
private List<Hasil> hasils;

//bi-directional many-to-one association to Kela
@ManyToOne
@JoinColumn(name="id_class")
private Kelas kelas;
}