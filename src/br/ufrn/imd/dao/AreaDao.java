package br.ufrn.imd.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.dominio.Area;

@Stateless
public class AreaDao {
	@PersistenceContext
    private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Area save(Area area) {
		if(area.getId() == 0)
			em.persist(area);
		else
			em.merge(area);
		return area;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Area area) {
		area = em.find(Area.class, area.getId());
		em.remove(area);
	}
	
	@SuppressWarnings("unchecked")
	public List<Area> list() {
		return (List<Area>) em.createQuery("select a from Area a").getResultList();
	}
}
