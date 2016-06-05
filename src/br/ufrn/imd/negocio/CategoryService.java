package br.ufrn.imd.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.ufrn.imd.dao.CategoryDao;
import br.ufrn.imd.dominio.Category;
import br.ufrn.imd.exceptions.NegocioException;

@Stateless
public class CategoryService {

	@Inject
	private CategoryDao categoryDao;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Category save(Category category) throws NegocioException{
	
		//verificar se a area existe
	
		Category categoryBD = categoryDao.searchCategory(category.getDescription());
		if(categoryBD == null || category.getId() > 0){
			categoryDao.save(category);
		}
		else{
			throw new NegocioException("Categoria já cadastrada.");
		}
		return category;
	}
		
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Category category){
		categoryDao.remove(category);
	}
	
	public List<Category> list(){
		return categoryDao.list();
	}
}
