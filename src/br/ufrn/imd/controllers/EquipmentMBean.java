package br.ufrn.imd.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import br.ufrn.imd.dao.EquipmentDao;
import br.ufrn.imd.dominio.Equipment;

@ManagedBean
@SessionScoped
public class EquipmentMBean {

	private Equipment equipment;
	
	private DataModel<Equipment> equipmentsModel;
	
	@Inject
	private EquipmentDao equipmentDao;
	
	public EquipmentMBean() {
		equipment = new Equipment();
	}
	
	public String newEquipment() {
		equipment = new Equipment();
		return "/views/equipment/form.jsf";
	}
	
	public String listEquipments() {
		equipmentsModel = new ListDataModel<Equipment>(equipmentDao.list());
		return "/views/equipment/list.jsf";
	}
	
	public String editEquipment(){
		equipment = equipmentsModel.getRowData();
		equipmentDao.save(equipment);
		return "/views/equipment/form.jsf";
	}
	
	public String addEquipment() {
		//employee.setUsuarioCadastro(usuarioMBean.getUsuarioLogado());
		equipmentDao.save(equipment);
		equipment = new Equipment();
		return "/views/equipment/list.jsf";
	}
	
	public String removeEquipment() {
		Equipment equpmentRemoved = equipmentsModel.getRowData();
		equipmentDao.remove(equpmentRemoved);
		equipmentsModel = new ListDataModel<Equipment>(equipmentDao.list());
		return "/views/equipment/list.jsf";
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public DataModel<Equipment> getEquipmentsModel() {
		return equipmentsModel;
	}

	public void setEquipmentsModel(DataModel<Equipment> equipmentsModel) {
		this.equipmentsModel = equipmentsModel;
	}
	
	
	
}
