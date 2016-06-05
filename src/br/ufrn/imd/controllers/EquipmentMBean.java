package br.ufrn.imd.controllers;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;


import br.ufrn.imd.dominio.Equipment;
import br.ufrn.imd.exceptions.NegocioException;
import br.ufrn.imd.negocio.EquipmentService;

@ManagedBean
@SessionScoped
public class EquipmentMBean {

	private Equipment equipment;
	
	private DataModel<Equipment> equipmentsModel;
	
	@EJB
	private EquipmentService equipmentService;
	
	public EquipmentMBean() {
		equipment = new Equipment();
	}
	
	public String newEquipment() {
		equipment = new Equipment();
		return "/views/equipment/form.jsf";
	}
	
	public String listEquipments() {
		equipmentsModel = new ListDataModel<Equipment>(equipmentService.list());
		return "/views/equipment/list.jsf";
	}
	
	public String editEquipment(){
		equipment = equipmentsModel.getRowData();
		try {
			equipmentService.save(equipment);
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
		}		
		return "/views/equipment/form.jsf";
	}
	
	public String addEquipment() {
		//equipment.setUsuarioCadastro(usuarioMBean.getUsuarioLogado());
		try {
			equipmentService.save(equipment);
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
		}
		equipment = new Equipment();
		return "/views/equipment/form.jsf";
	}
	
	public String removeEquipment() {
		Equipment equpmentRemoved = equipmentsModel.getRowData();
		equipmentService.remove(equpmentRemoved);
		equipmentsModel = new ListDataModel<Equipment>(equipmentService.list());
		return "/views/equipment/list.jsf";
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public DataModel<Equipment> getEquipmentsModel() {
		if(equipmentsModel == null){
			equipmentsModel = new ListDataModel<Equipment>(equipmentService.list());
		}
		return equipmentsModel;
	}

	public void setEquipmentsModel(DataModel<Equipment> equipmentsModel) {
		this.equipmentsModel = equipmentsModel;
	}
	
}
