package com.elearning.server.model;
import java.util.Date;
import java.sql.*;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity(name = "com.elearning.server.model.Soal")
@Table(name = "soal")
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
  private String tipe;
  @Column(name = "due_date", nullable = true)
  private Timestamp dueDate;
//bi-directional many-to-one association to Hasil
@OneToMany(mappedBy="soal")
private List<Hasil> hasils;

//bi-directional many-to-one association to Kela
@ManyToOne
@JoinColumn(name="id_class")
private Kelas kelas;
}