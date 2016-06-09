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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categories == null) ? 0 : categories.hashCode());
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
		if (categories == null) {
			if (CategoryConverter.categories != null)
				return false;
		} else if (!categories.equals(CategoryConverter.categories))
			return false;
		return true;
	}
}
