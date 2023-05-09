package com.springmvc.annotations;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public boolean saveUser(Register register) {
		Login login=new Login();
		Role role=new Role();
		role.setRole("ROLE_USER");
		role.setLogin(login);
		login.setUserName(register.getUserName());
		login.setPassword(register.getPassword());
		login.setValidFlag(Boolean.TRUE);
		login.setRegister(register);
		login.setRoles(Set.of(role));
		register.setLogin(login);
		Serializable serializable=hibernateTemplate.save(register);
		hibernateTemplate.save(role);
		return Objects.nonNull(serializable)?Boolean.TRUE:Boolean.FALSE;
	}

	public Login loadUserInfo(String userName) {
		@SuppressWarnings("unchecked")
		List<Login> find = (List<Login>) hibernateTemplate.find("from Login l where l.userName=?", userName);
		return !find.isEmpty()?find.get(0):null;
	}

}
