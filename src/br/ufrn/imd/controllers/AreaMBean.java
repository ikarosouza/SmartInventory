package br.ufrn.imd.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import br.ufrn.imd.dao.AreaDao;
import br.ufrn.imd.dominio.Area;

@ManagedBean
@SessionScoped
public class AreaMBean {

	private Area area;
	
	private DataModel<Area> areasModel;
	
	@Inject
	private AreaDao areaDao;
	
	public AreaMBean() {
		area = new Area();
	}
	
	public String newArea() {
		area = new Area();
		return "/views/area/form.jsf";
	}
	
	public String listAreas() {
		areasModel = new ListDataModel<Area>(areaDao.list());
		return "/views/area/list.jsf";
	}
	
	public String addArea() {
		//area.setUsuarioCadastro(usuarioMBean.getUsuarioLogado());
		areaDao.save(area);
		area = new Area();
		return "/views/area/form.jsf";
	}
	
	public String editArea(){
		area = areasModel.getRowData();
		areaDao.save(area);
		return "/views/area/form.jsf";
	}
	
	public String removeArea() {
		Area areaRemoved = areasModel.getRowData();
		areaDao.remove(areaRemoved);
		areasModel = new ListDataModel<Area>(areaDao.list());
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
			areasModel = new ListDataModel<Area>(areaDao.list());
		}
		return areasModel;
	}

	public void setAreasModel(DataModel<Area> areasModel) {
		this.areasModel = areasModel;
	}
	
}
