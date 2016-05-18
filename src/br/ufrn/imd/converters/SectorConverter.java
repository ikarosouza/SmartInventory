package br.ufrn.imd.converters;

import java.util.HashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufrn.imd.dominio.Sector;

@FacesConverter(forClass=Sector.class, value="sectorConverter")
public class SectorConverter implements Converter {

	private static HashMap<String, Sector> sectors;
	
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null) {
			return sectors.get(value);
		}
		return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {

		if (object != null && !"".equals(object)) {
			Sector entity = (Sector) object;
			if (entity.getId() > 0) {
				addSector(entity);
				return String.valueOf(entity.getId());
			}
		}
		return "";
	}

	private void addSector(Sector sector) {
		if(sectors == null) 
			sectors = new HashMap<>();
		if(!sectors.containsKey(String.valueOf(sector.getId())))
			sectors.put(String.valueOf(sector.getId()), sector);
	}

}
