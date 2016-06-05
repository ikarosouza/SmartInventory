package br.ufrn.imd.dao;

import java.util.List;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Sector;


@Stateless
public class SectorDao {
	
	@PersistenceContext
    private EntityManager em;
	
	public Sector save(Sector sector) {
		if(sector.getId() == 0)
			em.persist(sector);
		else
			em.merge(sector);
		return sector;
	}
	
	public void remove(Sector sector) {
		sector = em.find(Sector.class, sector.getId());
		em.remove(sector);
	}
	
	@SuppressWarnings("unchecked")
	public List<Sector> list() {
		return (List<Sector>) em.createQuery("select s from Sector s").getResultList();
	}
	
	public Sector searchSector(String name){
		String jpaql ="select s from Sector s" + " where s.area.name = :name";
		
		Query q = em.createQuery(jpaql);
		q.setParameter("name", name);
		
		try{
			return (Sector) q.getSingleResult();
		} catch (NoResultException e){
			return null;
		}
	}
}
