package br.ufrn.imd.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.dominio.Equipment;

@Stateless
public class EquipmentDao {
	@PersistenceContext
    private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Equipment save(Equipment equipment) {
		if(equipment.getTombo() == 0)
			em.persist(equipment);
		else
			em.merge(equipment);
		return equipment;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Equipment equipment) {
		equipment = em.find(Equipment.class, equipment.getTombo());
		em.remove(equipment);
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipment> list() {
		return (List<Equipment>) em.createQuery("select e from Equipment e").getResultList();
	}
}
