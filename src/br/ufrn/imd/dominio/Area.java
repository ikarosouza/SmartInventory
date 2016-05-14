package br.ufrn.imd.dominio;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

//@Entity
public class Area {
//	@Id @GeneratedValue
	private int id;
//	@NotNull
	private String name;
//	@OneToMany
	private ArrayList<Sector> sectors;
	
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
	public ArrayList<Sector> getSectors() {
		return sectors;
	}
	public void setSectors(ArrayList<Sector> sectors) {
		this.sectors = sectors;
	}
}
