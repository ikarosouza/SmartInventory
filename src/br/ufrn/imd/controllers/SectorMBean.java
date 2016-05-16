package br.ufrn.imd.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import br.ufrn.imd.dao.SectorDao;
import br.ufrn.imd.dominio.Sector;

@ManagedBean
@SessionScoped
public class SectorMBean {

	private Sector sector;
	
	private DataModel<Sector> sectorsModel;
	
	@Inject
	private SectorDao sectorDao;
	
	public SectorMBean() {
		sector = new Sector();
	}
	
	public String newSector() {
		sector = new Sector();
		return "/views/sector/form.jsf";
	}
	
	public String listSectors() {
		sectorsModel = new ListDataModel<Sector>(sectorDao.list());
		return "/views/sector/list.jsf";
	}
	
	public String addSector() {
		//sector.setUsuarioCadastro(usuarioMBean.getUsuarioLogado());
		sectorDao.save(sector);
		sector = new Sector();
		return "/views/sector/form.jsf";
	}
	
	public String removeSector() {
		Sector sectorRemoved = sectorsModel.getRowData();
		sectorDao.remove(sectorRemoved);
		sectorsModel = new ListDataModel<Sector>(sectorDao.list());
		return "/views/sector/list.jsf";
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public DataModel<Sector> getSectorsModel() {
		return sectorsModel;
	}

	public void setSectorsModel(DataModel<Sector> sectorsModel) {
		this.sectorsModel = sectorsModel;
	}

}
