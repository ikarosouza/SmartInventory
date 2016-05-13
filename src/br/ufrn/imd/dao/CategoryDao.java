package br.ufrn.imd.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.dominio.Category;

@Stateless
public class CategoryDao {
	@PersistenceContext
    private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Category save(Category category) {
		if(category.getId() == 0)
			em.persist(category);
		else
			em.merge(category);
		return category;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Category category) {
		category = em.find(Category.class, category.getId());
		em.remove(category);
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> list() {
		return (List<Category>) em.createQuery("select c from Category c").getResultList();
	}
}
