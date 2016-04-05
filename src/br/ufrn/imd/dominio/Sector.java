package br.ufrn.imd.dominio;

import java.util.ArrayList;

public class Sector {
	private int id;
	private String name;
	private ArrayList<Employee> employees;
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
