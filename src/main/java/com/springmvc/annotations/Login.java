package com.springmvc.annotations;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "login_id",insertable = false,updatable = false)
	private Long id;
	
	@NotEmpty
	@Column
	private String userName;
	
	@NotEmpty
	@Column
	private String password;
	
	@OneToOne(mappedBy = "register")
	private Register register;
	
	@Column
	private boolean validFlag;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "login_id")
	private Set<Role> roles= new HashSet<>();
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, password, register, userName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(register, other.register) && Objects.equals(userName, other.userName);
	}
	public Register getRegister() {
		return register;
	}
	public void setRegister(Register register) {
		this.register = register;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public boolean isValidFlag() {
		return validFlag;
	}
	public void setValidFlag(boolean validFlag) {
		this.validFlag = validFlag;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
