package br.ufrn.imd.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import br.ufrn.imd.dao.CategoryDao;
import br.ufrn.imd.dominio.Category;

@ManagedBean
@SessionScoped
public class CategoryMBean {
	private Category category;
	private DataModel<Category> categoriesModel;
	
	@Inject
	private CategoryDao categoryDao;
	
	public CategoryMBean() {
		category = new Category();
	}
	
	public String newCategory() {
		category = new Category();
		return "/views/category/form.jsf";
	}
	
	public String listCategories() {
		categoriesModel = new ListDataModel<Category>(categoryDao.list());
		return "/views/category/list.jsf";
	}
	
	public String addCategory() {
		//category.setUsuarioCadastro(usuarioMBean.getUsuarioLogado());
		categoryDao.save(category);
		category = new Category();
		return "/views/category/list.jsf";
	}
	
	public String editCategory(){
		category = categoriesModel.getRowData();
		categoryDao.save(category);
		return "/views/category/form.jsf";
	}
	
	public String removeCategory() {
		Category categoryRemoved = categoriesModel.getRowData();
		categoryDao.remove(categoryRemoved);
		categoriesModel = new ListDataModel<Category>(categoryDao.list());
		return "/views/category/list.jsf";
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public DataModel<Category> getCategoriesModel() {
		return categoriesModel;
	}

	public void setCategoriesModel(DataModel<Category> categoriesModel) {
		this.categoriesModel = categoriesModel;
	}	
	
}

