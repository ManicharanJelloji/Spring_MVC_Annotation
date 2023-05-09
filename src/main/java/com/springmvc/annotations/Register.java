package com.springmvc.annotations;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


@Entity
@Table
public class Register implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "register_id")
	private Long id;
	
	@Size(min=3,max=20,message="FirstName must be between 3 and 20 characters")
	@Column
	private String firstName;
	
	@Size(min=3,max=20,message="LastName must be between 3 and 20 characters")
	@Column
	private String lastName;
	
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",message="Invalid Email Address")
	@Column
	private String email;
	
	@Size(min=3,max=20,message="UserName must be between 3 and 20 characters")
	@Embedded
	private String userName;
	
	@Size(min=3,max=20,message="Password must be between 3 and 20 characters")
	@Embedded
	private String password;
	
	@Size(min=3,max=20,message="ConfirmPassword must be between 3 and 20 characters")
	@Embedded
	private String confirmPassword;
	
	@Embedded
	private CommonsMultipartFile file;
	
	@Lob
	private byte[] image;
	
	@Transient
	private String base64;
	
	@OneToOne(mappedBy = "register",cascade = CascadeType.ALL)
	private Login login;
	
	
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	
	
}
