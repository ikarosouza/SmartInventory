package br.ufrn.imd.controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;


import br.ufrn.imd.dominio.Employee;
import br.ufrn.imd.exceptions.NegocioException;
import br.ufrn.imd.negocio.EmployeeService;

@ManagedBean
@SessionScoped
public class EmployeeMBean {

	private Employee employee;
	
	private DataModel<Employee> employeesModel;
	
	@EJB
	private EmployeeService employeeService;
	
	public EmployeeMBean() {
		employee = new Employee();
	}
	
	public String newEmployee() {
		employee = new Employee();
		return "/views/employee/form.jsf";
	}
	
	public String listEmployees() {
		employeesModel = new ListDataModel<Employee>(employeeService.list());
		return "/views/employee/list.jsf";
	}
	
	public String addEmployee() {
		//employee.setUsuarioCadastro(usuarioMBean.getUsuarioLogado());
		try {
			employeeService.save(employee);
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
		}
		employee = new Employee();
		return "/views/employee/form.jsf";
	}
	
	public String editEmployee(){
		employee = employeesModel.getRowData();
		try {
			employeeService.save(employee);
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
		}		
		return "/views/employee/form.jsf";
	}
	
	public String removeEmployee() {
		Employee employeeRemoved = employeesModel.getRowData();
		employeeService.remove(employeeRemoved);
		employeesModel = new ListDataModel<Employee>(employeeService.list());
		return "/views/employee/list.jsf";
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public DataModel<Employee> getEmployeesModel() {
		if(employeesModel == null){
			employeesModel = new ListDataModel<Employee>(employeeService.list());
		}
		return employeesModel;
	}

	public void setEmployeesModel(DataModel<Employee> employeesModel) {
		this.employeesModel = employeesModel;
	}
	
}
