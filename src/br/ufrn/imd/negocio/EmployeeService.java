package br.ufrn.imd.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.ufrn.imd.dao.EmployeeDao;
import br.ufrn.imd.dominio.Employee;
import br.ufrn.imd.exceptions.NegocioException;

@Stateless
public class EmployeeService {

	@Inject
	private EmployeeDao employeeDao;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Employee save(Employee employee) throws NegocioException{
	
		//verificar se o empregado existe
	
		Employee employeeBD = employeeDao.searchEmployee(employee.getMatricula());
		if(employeeBD == null || employee.getMatricula() > 0 && employee.getMatricula() != employeeBD.getMatricula()){
			employeeDao.save(employee);
		}
		else
			if(employeeBD != null && employee.getMatricula() > 0){
				employeeDao.save(employee);
			}
		else{						
			throw new NegocioException("Funcionário já cadastrado.");
		}
		return employee;
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Employee employee){
		employeeDao.remove(employee);
	}
	
	public List<Employee> list(){
		return employeeDao.list();
	}
}
