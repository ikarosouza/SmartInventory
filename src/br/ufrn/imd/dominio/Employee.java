package br.ufrn.imd.dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Employee {
	@Id
	private int matricula;
	@NotNull
	private String name;
	private String role;
	@OneToMany
	private List<Equipment> equipments;
	@ManyToOne
	private Sector sector;
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public List<Equipment> getEquipments() {
		return equipments;
	}
	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
}
