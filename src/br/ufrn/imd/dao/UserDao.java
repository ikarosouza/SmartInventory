package br.ufrn.imd.dao;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrn.imd.dominio.User;

@Stateless
public class UserDao {

	@PersistenceContext
	private EntityManager em;

	public User save(User user) {
		if(user.getMatricula() == 0)
			em.persist(user);
		else
			em.merge(user);
		return user;
	}
	
	public void remove(User user) {
		user = em.find(User.class, user.getMatricula());
		em.remove(user);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> list() {
		return (List<User>) em.createQuery("select u from User u").getResultList();
	}

	public User searchLogin(String login) {
		
		try {
			
			Query q = em.createQuery("select u from User u where login = :login");
			q.setParameter("login", login);
		
			return (User) q.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}

	public User searchUser(int matricula){
		String jpaql ="select u from User u" + " where u.matricula = :matricula";
		
		Query q = em.createQuery(jpaql);
		q.setParameter("matricula", matricula);
		
		try{
			return (User) q.getSingleResult();
		} catch (NoResultException e){
			return null;
	}
}

}