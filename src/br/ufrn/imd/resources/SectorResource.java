package br.ufrn.imd.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import br.ufrn.imd.dominio.Sector;
import br.ufrn.imd.negocio.SectorService;

@Stateless
@Path("/consulta")
public class SectorResource {
	@EJB
	private SectorService service;
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/sectors")
	public List<Sector> list(){
		return service.list();
	}
}
