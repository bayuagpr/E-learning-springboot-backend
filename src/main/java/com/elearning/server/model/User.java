package com.elearning.server.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity(name = "com.elearning.server.model.User")
@Table(name = "user")
public class User implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "id", nullable = false)
  private String id;
  @Column(name = "email", nullable = true)
  private String email;
  @Column(name = "password", nullable = true)
  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;
  //bi-directional one-to-one association to Dosen
  @OneToOne(mappedBy="user")
  @JsonBackReference(value="userDosen")
	private Dosen dosen;

	//bi-directional one-to-one association to Mahasiswa
  @OneToOne(mappedBy="user")
  @JsonBackReference(value="userMahasiswa")
	private Mahasiswa mahasiswa;

	//bi-directional many-to-one association to Role
	@ManyToOne
  @JoinColumn(name="id_role")
	private Role role;
}