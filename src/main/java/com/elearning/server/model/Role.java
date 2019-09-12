package com.elearning.server.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "com.elearning.server.model.Role")
@Table(name = "role")
public class Role implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;
  @Column(name = "nama", nullable = true)
  private String nama;
  @Column(name = "deskripsi", nullable = true)
  private String deskripsi;
  //bi-directional many-to-one association to User
	@OneToMany(mappedBy="role")
	private List<User> users;
}