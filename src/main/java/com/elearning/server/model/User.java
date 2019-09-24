package com.elearning.server.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity(name = "com.elearning.server.model.User")
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = User.class)
public class User implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "id", nullable = false)
  private String id;
  @Column(name = "email", nullable = true)
  private String email;
  @JsonIgnore
  @Column(name = "password", nullable = true)
  private String password;
  //bi-directional one-to-one association to Dosen
	@OneToOne(mappedBy="user")
	private Dosen dosen;

	//bi-directional one-to-one association to Mahasiswa
	@OneToOne(mappedBy="user")
	private Mahasiswa mahasiswa;

	//bi-directional many-to-one association to Role
	@ManyToOne
  @JoinColumn(name="id_role")
	private Role role;
}