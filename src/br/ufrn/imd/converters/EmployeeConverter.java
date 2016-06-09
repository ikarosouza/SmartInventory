package br.ufrn.imd.converters;

import java.util.HashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufrn.imd.dominio.Employee;

@FacesConverter(forClass=Employee.class, value="employeeConverter")
public class EmployeeConverter implements Converter {

	private static HashMap<String, Employee> employees;
	
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null) {
			return employees.get(value);
		}
		return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {

		if (object != null && !"".equals(object)) {
			Employee entity = (Employee) object;
			if (entity.getMatricula() > 0) {
				addEmployee(entity);
				return String.valueOf(entity.getMatricula());
			}
		}
		return "";
	}

	private void addEmployee(Employee employee) {
		if(employees == null) 
			employees = new HashMap<>();
		if(!employees.containsKey(String.valueOf(employee.getMatricula())))
			employees.put(String.valueOf(employee.getMatricula()), employee);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employees == null) ? 0 : employees.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (employees == null) {
			if (EmployeeConverter.employees != null)
				return false;
		} else if (!employees.equals(EmployeeConverter.employees))
			return false;
		return true;
	}
}
