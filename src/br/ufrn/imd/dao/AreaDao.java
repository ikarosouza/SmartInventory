package br.ufrn.imd.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Area;

@Stateless
public class AreaDao {
	
	@PersistenceContext
    private EntityManager em;
	
	
	public Area save(Area area) {
		if(area.getId() == 0)
			em.persist(area);
		else
			em.merge(area);
		return area;
	}
	
	
	public void remove(Area area) {
		area = em.find(Area.class, area.getId());
		em.remove(area);
	}
	
	@SuppressWarnings("unchecked")
	public List<Area> list() {
		return (List<Area>) em.createQuery("select a from Area a").getResultList();
	}
	
	public Area searchArea(String name){
		String jpaql ="select a from Area a" + " where a.name = :name";
		
		Query q = em.createQuery(jpaql);
		q.setParameter("name", name);
		
		try{
			return (Area) q.getSingleResult();
		} catch (NoResultException e){
			return null;
		}
	}
	
}
