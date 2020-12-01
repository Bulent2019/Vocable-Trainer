package com.example.Project.model;

import java.util.Set;

import javax.persistence.*;

import com.example.Project.model.Picture;

@Entity
@Table(name = "users")   //  without => error something like, ...error near field user!!
public class MyUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String passwordHash;
	
	@Column(name = "role", nullable = false)
	private String role;
	
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private Set<Picture> pictureList;
	
	public MyUser() {
		
	}

	public MyUser(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

//	public Set<Picture> getPictureList() {
//		return pictureList;
//	}
//
//	public void setPictureList(Set<Picture> pictureList) {
//		this.pictureList = pictureList;
//	}
}
