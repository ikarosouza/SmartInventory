package br.ufrn.imd.dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Area {
	@Id @GeneratedValue
	private int id;
	@NotNull
	private String name;
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "area")
	private List<Sector> sector;
	
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
	public List<Sector> getSector() {
		return sector;
	}
	public void setSector(List<Sector> sector) {
		this.sector = sector;
	}	
}
