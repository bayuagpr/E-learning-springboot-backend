package com.elearning.server.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import java.util.List;
import java.io.Serializable;

@Data
@Entity(name = "com.elearning.server.model.Enrollment")
@Table(name = "enrollment")
public class Enrollment implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "id", nullable = false)
  private String id;
  @Column(name = "disetujui", nullable = true)
  private boolean disetujui;
 //bi-directional many-to-one association to Kela
 @ManyToOne
 @JoinColumn(name="id_class")
 private Kelas kelas;

 //bi-directional many-to-one association to Mahasiswa
 @ManyToOne
 @JoinColumn(name="id_mahasiswa")
 private Mahasiswa mahasiswa;
}