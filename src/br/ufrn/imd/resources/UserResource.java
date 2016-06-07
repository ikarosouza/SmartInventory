package br.ufrn.imd.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.ufrn.imd.dominio.User;
import br.ufrn.imd.negocio.UserService;

@Stateless
@Path("/consulta")
public class UserResource {

	@EJB
	private UserService service;
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/users")
	public List<User> list(){
		return service.list();
	}
}
