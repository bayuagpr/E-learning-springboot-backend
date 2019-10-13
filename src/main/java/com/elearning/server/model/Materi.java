package com.elearning.server.model;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import java.io.Serializable;

@Data
@Entity(name = "com.elearning.server.model.Materi")
@Table(name = "materi")
@EntityListeners(AuditingEntityListener.class)
public class Materi implements Serializable{
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
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "creation_date", nullable = false)
  protected Date creationDate;
  //bi-directional many-to-one association to Kela
	@ManyToOne
  @JoinColumn(name="id_class")
	private Kelas kelas;
}