package br.ufrn.imd.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.ufrn.imd.dao.EquipmentDao;
import br.ufrn.imd.dominio.Equipment;
import br.ufrn.imd.exceptions.NegocioException;

@Stateless
public class EquipmentService {

	@Inject
	private EquipmentDao equipmentDao;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Equipment save(Equipment equipment) throws NegocioException{
	
		//verificar se o equipamento existe
	
		Equipment equipmentBD = equipmentDao.searchEquipment(equipment.getSerialNumber());
		if(equipmentBD == null || equipment.getTombo() > 0){
			equipmentDao.save(equipment);
		}
		else{
			throw new NegocioException("Equipamento já cadastrado.");
		}
		return equipment;
	}
		
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Equipment equipment){
		equipmentDao.remove(equipment);
	}
	
	public List<Equipment> list(){
		return equipmentDao.list();
	}
}
