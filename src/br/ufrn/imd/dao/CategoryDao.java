package br.ufrn.imd.dao;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Category;


@Stateless
public class CategoryDao {
	
	@PersistenceContext
    private EntityManager em;
	
	
	public Category save(Category category) {
		if(category.getId() == 0)
			em.persist(category);
		else
			em.merge(category);
		return category;
	}
	
	
	public void remove(Category category) {
		category = em.find(Category.class, category.getId());
		em.remove(category);
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> list() {
		return (List<Category>) em.createQuery("select c from Category c").getResultList();
	}
	
	public Category searchCategory(String description){
		String jpaql ="select c from Category c" + " where c.description = :description";
		
		Query q = em.createQuery(jpaql);
		q.setParameter("description", description);
		
		try{
			return (Category) q.getSingleResult();
		} catch (NoResultException e){
			return null;
		}
	}
	
}
