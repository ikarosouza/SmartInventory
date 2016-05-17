package br.ufrn.imd.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import br.ufrn.imd.dao.EmployeeDao;
import br.ufrn.imd.dominio.Employee;

@ManagedBean
@SessionScoped
public class EmployeeMBean {

	private Employee employee;
	
	private DataModel<Employee> employeesModel;
	
	@Inject
	private EmployeeDao employeeDao;
	
	public EmployeeMBean() {
		employee = new Employee();
	}
	
	public String newEmployee() {
		employee = new Employee();
		return "/views/employee/form.jsf";
	}
	
	public String listEmployees() {
		employeesModel = new ListDataModel<Employee>(employeeDao.list());
		return "/views/employee/list.jsf";
	}
	
	public String addEmployee() {
		//employee.setUsuarioCadastro(usuarioMBean.getUsuarioLogado());
		employeeDao.save(employee);
		employee = new Employee();
		return "/views/employee/form.jsf";
	}
	
	public String editEmployee(){
		employee = employeesModel.getRowData();
		employeeDao.save(employee);
		return "/views/employee/form.jsf";
	}
	
	public String removeEmployee() {
		Employee employeeRemoved = employeesModel.getRowData();
		employeeDao.remove(employeeRemoved);
		employeesModel = new ListDataModel<Employee>(employeeDao.list());
		return "/views/employee/list.jsf";
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public DataModel<Employee> getEmployeesModel() {
		return employeesModel;
	}

	public void setEmployeesModel(DataModel<Employee> employeesModel) {
		this.employeesModel = employeesModel;
	}
	
	
}
