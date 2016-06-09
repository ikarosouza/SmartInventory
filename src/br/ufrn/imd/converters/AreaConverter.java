package br.ufrn.imd.converters;

import java.util.HashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufrn.imd.dominio.Area;

@FacesConverter(forClass=Area.class, value="areaConverter")
public class AreaConverter implements Converter {

	private static HashMap<String, Area> areas;
	
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null) {
			return areas.get(value);
		}
		return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {

		if (object != null && !"".equals(object)) {
			Area entity = (Area) object;
			if (entity.getId() > 0) {
				addArea(entity);
				return String.valueOf(entity.getId());
			}
		}
		return "";
	}

	private void addArea(Area area) {
		if(areas == null) 
			areas = new HashMap<>();
		if(!areas.containsKey(String.valueOf(area.getId())))
			areas.put(String.valueOf(area.getId()), area);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((areas == null) ? 0 : areas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (areas == null) {
			if (AreaConverter.areas != null)
				return false;
		} else if (!areas.equals(AreaConverter.areas))
			return false;
		return true;
	}
}

