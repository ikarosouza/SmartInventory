package br.ufrn.imd.controllers;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.ufrn.imd.dominio.Area;
import br.ufrn.imd.exceptions.NegocioException;
import br.ufrn.imd.negocio.AreaService;

@ManagedBean
@SessionScoped
public class AreaMBean {

	private Area area;
	
	private DataModel<Area> areasModel;
	
	@EJB
	private AreaService areaService;
	
	public AreaMBean() {
		area = new Area();
	}
	
	public String newArea() {
		area = new Area();
		return "/views/area/form.jsf";
	}
	
	public String listAreas() {
		areasModel = new ListDataModel<Area>(areaService.list());
		return "/views/area/list.jsf";
	}
	
	public String addArea() {
		//area.setUsuarioCadastro(usuarioMBean.getUsuarioLogado());
		try {
			areaService.save(area);
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);			
		}
		area = new Area();
		return "/views/area/form.jsf";
	}
	
	public String editArea(){
		area = areasModel.getRowData();
		try {
			areaService.save(area);
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
		}		
		return "/views/area/form.jsf";
	}
	
	public String removeArea() {
		Area areaRemoved = areasModel.getRowData();
		areaService.remove(areaRemoved);
		areasModel = new ListDataModel<Area>(areaService.list());
		return "/views/Area/list.jsf";
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public DataModel<Area> getAreasModel() {
		if(areasModel == null){
			areasModel = new ListDataModel<Area>(areaService.list());
		}
		return areasModel;
	}

	public void setAreasModel(DataModel<Area> areasModel) {
		this.areasModel = areasModel;
	}
	
}
