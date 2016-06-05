package br.ufrn.imd.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Usuario")
public class User {
	
	@Id
	private int matricula;
	@NotNull
	private String name;
	@NotNull @Column(unique=true)
	private String login;
	@NotNull
	private String password;
	private boolean admin;
	
	public User(){}
	
	public User(int matricula, String login, String password) {	
		this.matricula = matricula;
		this.login = login;
		this.password = password;
	}
	
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
