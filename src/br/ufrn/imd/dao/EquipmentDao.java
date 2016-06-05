package br.ufrn.imd.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Equipment;

@Stateless
public class EquipmentDao {
	
	@PersistenceContext
    private EntityManager em;
	
	
	public Equipment save(Equipment equipment) {
		if(equipment.getTombo() == 0)
			em.persist(equipment);
		else
			em.merge(equipment);
		return equipment;
	}
	
	
	public void remove(Equipment equipment) {
		equipment = em.find(Equipment.class, equipment.getTombo());
		em.remove(equipment);
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipment> list() {
		return (List<Equipment>) em.createQuery("select e from Equipment e").getResultList();
	}
	
	public Equipment searchEquipment(String serialNumber){
		String jpaql ="select e from Equipment e" + " where e.serialNumber = :serialNumber";
		
		Query q = em.createQuery(jpaql);
		q.setParameter("serialNumber", serialNumber);
		
		try{
			return (Equipment) q.getSingleResult();
		} catch (NoResultException e){
			return null;
		}
	}
}
