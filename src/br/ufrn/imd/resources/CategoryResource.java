package br.ufrn.imd.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.ufrn.imd.dominio.Category;
import br.ufrn.imd.negocio.CategoryService;

@Stateless
@Path("/consulta")
public class CategoryResource {

	@EJB
	private CategoryService service;
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/categories")
	public List<Category> list(){
		return service.list();
	}
}
