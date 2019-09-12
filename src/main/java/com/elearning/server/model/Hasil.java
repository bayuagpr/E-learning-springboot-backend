package com.elearning.server.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity(name = "com.elearning.server.model.Hasil")
@Table(name = "hasil")
public class Hasil implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", nullable = false)
  private String id;
  @Column(name = "isi_jawaban", nullable = true)
  private String isiJawaban;
  @Column(name = "attachment", nullable = true)
  private String attachment;
  @Column(name = "nilai", nullable = true)
  private String nilai;
  @Column(name = "komentar", nullable = true)
  private String komentar;
 //bi-directional many-to-one association to Mahasiswa
 @ManyToOne
 @JoinColumn(name="id_mahasiswa")
 private Mahasiswa mahasiswa;

 //bi-directional many-to-one association to Soal
 @ManyToOne
 @JoinColumn(name="id_soal")
 private Soal soal;
}