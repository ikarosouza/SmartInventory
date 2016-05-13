package br.ufrn.imd.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.dominio.Sector;

@Stateless
public class SectorDao {
	@PersistenceContext
    private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Sector save(Sector sector) {
		if(sector.getId() == 0)
			em.persist(sector);
		else
			em.merge(sector);
		return sector;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Sector sector) {
		sector = em.find(Sector.class, sector.getId());
		em.remove(sector);
	}
	
	@SuppressWarnings("unchecked")
	public List<Sector> list() {
		return (List<Sector>) em.createQuery("select s from Sector s").getResultList();
	}
}
