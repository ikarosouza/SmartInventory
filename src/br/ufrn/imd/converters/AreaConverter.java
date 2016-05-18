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

}

