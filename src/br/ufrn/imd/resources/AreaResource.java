package br.ufrn.imd.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.ufrn.imd.dominio.Area;
import br.ufrn.imd.negocio.AreaService;

@Stateless
@Path("/consulta")
public class AreaResource {

	@EJB
	private AreaService service;
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/areas")
	public List<Area> list(){
		return service.list();
	}
	
}
