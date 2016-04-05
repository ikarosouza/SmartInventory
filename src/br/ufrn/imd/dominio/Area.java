package br.ufrn.imd.dominio;

import java.util.ArrayList;

public class Area {
	private int id;
	private String name;
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
