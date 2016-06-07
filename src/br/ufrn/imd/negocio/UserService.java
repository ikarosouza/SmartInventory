package br.ufrn.imd.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.ufrn.imd.dao.UserDao;
import br.ufrn.imd.dominio.User;
import br.ufrn.imd.exceptions.NegocioException;

@Stateless
public class UserService {

	@Inject
	private UserDao userDao;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User save(User user) throws NegocioException{
	
		//verificar se a area existe
	
		User userBD = userDao.searchUser(user.getMatricula());
		if(userBD == null || user.getMatricula() > 0 && userBD.getMatricula() != user.getMatricula()){
			userDao.save(user);
		}
		else{
			throw new NegocioException("Usuário já cadastrado.");
		}
		return user;
	}
		
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(User user){
		userDao.remove(user);
	}
	
	public List<User> list(){
		return userDao.list();
	}

	public User authenticateLogin(User user) {
		
		User userBD = userDao.searchLogin(user.getLogin());
		
		if(userBD != null){
			return userBD;
		}
		else{
			return null;	
		}
		
	}
}
