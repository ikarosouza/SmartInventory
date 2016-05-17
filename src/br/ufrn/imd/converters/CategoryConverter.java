package br.ufrn.imd.converters;

import java.util.HashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufrn.imd.dominio.Category;

@FacesConverter(forClass=Category.class, value="categoryConverter")
public class CategoryConverter implements Converter {

	private static HashMap<String, Category> categories;
	
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null) {
			return categories.get(value);
		}
		return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {

		if (object != null && !"".equals(object)) {
			Category entity = (Category) object;
			if (entity.getId() > 0) {
				addCategory(entity);
				return String.valueOf(entity.getId());
			}
		}
		return "";
	}

	private void addCategory(Category category) {
		if(categories == null) 
			categories = new HashMap<>();
		if(!categories.containsKey(String.valueOf(category.getId())))
			categories.put(String.valueOf(category.getId()), category);
	}

}
