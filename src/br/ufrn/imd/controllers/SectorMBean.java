package br.ufrn.imd.controllers;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.ufrn.imd.dominio.Sector;
import br.ufrn.imd.exceptions.NegocioException;
import br.ufrn.imd.negocio.SectorService;

@ManagedBean
@SessionScoped
public class SectorMBean {

	private Sector sector;
	
	private DataModel<Sector> sectorsModel;
	
	@EJB
	private SectorService sectorService;
	
	public SectorMBean() {
		sector = new Sector();
	}
	
	public String newSector() {
		sector = new Sector();
		return "/views/sector/form.jsf";
	}
	
	public String listSectors() {
		sectorsModel = new ListDataModel<Sector>(sectorService.list());
		return "/views/sector/list.jsf";
	}
	
	public String addSector() {
		//sector.setUsuarioCadastro(usuarioMBean.getUsuarioLogado());
		try {
			sectorService.save(sector);
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
		}
		sector = new Sector();
		return "/views/sector/form.jsf";
	}
	
	public String editSector(){
		sector = sectorsModel.getRowData();
		try {
			sectorService.save(sector);
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
		}		
		return "/views/sector/form.jsf";
	}
	
	public String removeSector() {
		Sector sectorRemoved = sectorsModel.getRowData();
		sectorService.remove(sectorRemoved);
		sectorsModel = new ListDataModel<Sector>(sectorService.list());
		return "/views/sector/list.jsf";
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public DataModel<Sector> getSectorsModel() {
		if(sectorsModel == null){
			sectorsModel = new ListDataModel<Sector>(sectorService.list());
		}
		return sectorsModel;
	}

	public void setSectorsModel(DataModel<Sector> sectorsModel) {
		this.sectorsModel = sectorsModel;
	}

}
