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

}
