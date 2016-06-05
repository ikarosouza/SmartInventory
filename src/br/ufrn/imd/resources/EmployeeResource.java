package br.ufrn.imd.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.ufrn.imd.dominio.Employee;
import br.ufrn.imd.negocio.EmployeeService;

@Stateless
@Path("/consulta")
public class EmployeeResource {
	@EJB
	private EmployeeService service;
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/employees")
	public List<Employee> list(){
		return service.list();
	}
}
