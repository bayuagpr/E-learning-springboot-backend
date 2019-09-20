package com.elearning.server.model;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "com.elearning.server.model.Role")
@Table(name = "role")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Role.class)
public class Role implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;
  @Column(name = "nama", nullable = true)
  @Enumerated(EnumType.STRING)
  private NamaRole nama;
  @Column(name = "deskripsi", nullable = true)
  private String deskripsi;
  //bi-directional many-to-one association to User
  @OneToMany(mappedBy="role")
  @JsonBackReference(value="userRole")
	private List<User> users;
}