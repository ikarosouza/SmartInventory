package br.ufrn.imd.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.ufrn.imd.dao.SectorDao;
import br.ufrn.imd.dominio.Sector;
import br.ufrn.imd.exceptions.NegocioException;

@Stateless
public class SectorService {
	
	@Inject
	private SectorDao sectorDao;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Sector save(Sector sector) throws NegocioException{
	
		//verificar se o setor existe
	
		List<Sector> sectors = sectorDao.searchSector(sector.getName());
		for(Sector sectorBD : sectors){
			if(sectorBD == null && sector.getId() > 0){
				sectorDao.save(sector);
			}
			else {
				if(sector.getArea().getName().equals(sectorBD.getArea().getName()))
					throw new NegocioException("Setor j� cadastrado.");
				else
					sectorDao.save(sector);
			}
		}
		return sector;
	}
		
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Sector sector){
		sectorDao.remove(sector);
	}
	
	public List<Sector> list(){
		return sectorDao.list();
	}
}