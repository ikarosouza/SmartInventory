package br.ufrn.imd.dao;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.dominio.Employee;

public class EmployeeDao {
	@PersistenceContext
    private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Employee save(Employee employee) {
		if(employee.getMatricula() == 0)
			em.persist(employee);
		else
			em.merge(employee);
		return employee;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Employee employee) {
		employee = em.find(Employee.class, employee.getMatricula());
		em.remove(employee);
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> list() {
		return (List<Employee>) em.createQuery("select e from Employee e").getResultList();
	}
}
