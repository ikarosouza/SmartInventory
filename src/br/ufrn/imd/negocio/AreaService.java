package br.ufrn.imd.negocio;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.ufrn.imd.dao.AreaDao;
import br.ufrn.imd.dominio.Area;
import br.ufrn.imd.exceptions.NegocioException;

@Stateless
public class AreaService {

	@Inject
	private AreaDao areaDao;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Area save(Area area) throws NegocioException{
	
		//verificar se a area existe
	
		Area areaBD = areaDao.searchArea(area.getName());
		if((areaBD == null && area.getId() >= 0)){
			areaDao.save(area);
		}
		else{
			if(areaBD.getName().equals(area.getName()) && areaBD.getId() != area.getId())
				throw new NegocioException("Area já cadastrada.");
			else
				areaDao.save(area);
		}
		return area;
	}
		
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Area area){
		areaDao.remove(area);
	}
	
	public List<Area> list(){
		return areaDao.list();
	}
	
}
