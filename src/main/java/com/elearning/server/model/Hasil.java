package com.elearning.server.model;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.springframework.data.annotation.LastModifiedDate;

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
  @Column(name = "status", nullable = true)
  @Enumerated(EnumType.STRING)
  private StatusHasil status;
//  @LastModifiedDate
  @Column(name = "last_modified", nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastModified;
 //bi-directional many-to-one association to Mahasiswa

  @Column(name = "ternilai", nullable = true)
  private boolean ternilai;
 @ManyToOne
 @JoinColumn(name="id_mahasiswa")
 private Mahasiswa mahasiswa;

 //bi-directional many-to-one association to Soal
 @ManyToOne
 @JoinColumn(name="id_soal")
 private Soal soal;
 
 public boolean getTernilai() {
  // TODO Auto-generated method stub
  return ternilai;
}

}