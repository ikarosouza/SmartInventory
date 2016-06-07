package br.ufrn.imd.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Employee;


@Stateless
public class EmployeeDao {
	@PersistenceContext
    private EntityManager em;
	
	
	public Employee save(Employee employee) {
		if(employee.getMatricula() == 0)
			em.persist(employee);
		else
			em.merge(employee);
		return employee;
	}
	
	
	public void remove(Employee employee) {
		employee = em.find(Employee.class, employee.getMatricula());
		em.remove(employee);
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> list() {
		return (List<Employee>) em.createQuery("select e from Employee e").getResultList();
	}
	
	public Employee searchEmployee( int matricula){
				
		String jpaql ="select e from Employee e" + " where e.matricula = :matricula";
		
		Query q = em.createQuery(jpaql);
		q.setParameter("matricula", matricula);
				
		
		try{
			return (Employee) q.getSingleResult();
		} catch (NoResultException e){
			return null;
		}
	}
}
