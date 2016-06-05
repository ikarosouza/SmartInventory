package br.ufrn.imd.controllers;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;


import br.ufrn.imd.dominio.Category;
import br.ufrn.imd.exceptions.NegocioException;
import br.ufrn.imd.negocio.CategoryService;

@ManagedBean
@SessionScoped
public class CategoryMBean {
	
	private Category category;
	
	private DataModel<Category> categoriesModel;
	
	@EJB
	private CategoryService categoryService;
	
	public CategoryMBean() {
		category = new Category();
	}
	
	public String newCategory() {
		category = new Category();
		return "/views/category/form.jsf";
	}
	
	public String listCategories() {
		categoriesModel = new ListDataModel<Category>(categoryService.list());
		return "/views/category/list.jsf";
	}
	
	public String addCategory() {
		//category.setUsuarioCadastro(usuarioMBean.getUsuarioLogado());
		try {
			categoryService.save(category);
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
		}
		category = new Category();
		return "/views/category/form.jsf";
	}
	
	public String editCategory(){
		category = categoriesModel.getRowData();
		try {
			categoryService.save(category);
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
		}		
		return "/views/category/form.jsf";
	}
	
	public String removeCategory() {
		Category categoryRemoved = categoriesModel.getRowData();
		categoryService.remove(categoryRemoved);
		categoriesModel = new ListDataModel<Category>(categoryService.list());
		return "/views/category/list.jsf";
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public DataModel<Category> getCategoriesModel() {
		if(categoriesModel == null){
			categoriesModel = new ListDataModel<Category>(categoryService.list());
		}
		return categoriesModel;
	}

	public void setCategoriesModel(DataModel<Category> categoriesModel) {
		this.categoriesModel = categoriesModel;
	}	
	
}

