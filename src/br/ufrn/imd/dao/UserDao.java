package br.ufrn.imd.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrn.imd.dominio.User;

@Stateless
public class UserDao {

	@PersistenceContext
	private EntityManager em;

	public User searchLogin(String login) {
		
		try {
			
			Query q = em.createQuery("select u from User u where login = :login");
			q.setParameter("login", login);
		
			return (User) q.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User save(User user) {
		if(user.getMatricula() == 0)
			em.persist(user);
		else
			em.merge(user);
		return user;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(User user) {
		user = em.find(User.class, user.getMatricula());
		em.remove(user);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> list() {
		return (List<User>) em.createQuery("select u from User u").getResultList();
	}
	
}
