package br.ufrn.imd.dominio;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Sector {
	@Id @GeneratedValue
	private int id;
	@NotNull
	private String name;
	private ArrayList<Employee> employees;
	@OneToMany
	private ArrayList<Equipment> equipments;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}
	public ArrayList<Equipment> getEquipments() {
		return equipments;
	}
	public void setEquipments(ArrayList<Equipment> equipments) {
		this.equipments = equipments;
	}
}
