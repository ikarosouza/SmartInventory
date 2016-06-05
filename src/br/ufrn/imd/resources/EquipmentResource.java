package br.ufrn.imd.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.ufrn.imd.dominio.Equipment;
import br.ufrn.imd.negocio.EquipmentService;

@Stateless
@Path("/consulta")
public class EquipmentResource {
	@EJB
	private EquipmentService service;
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/equipments")
	public List<Equipment> list(){
		return service.list();
	}
}
