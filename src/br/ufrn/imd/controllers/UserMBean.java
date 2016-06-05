package br.ufrn.imd.controllers;

import javax.faces.application.FacesMessage;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import br.ufrn.imd.dao.UserDao;
import br.ufrn.imd.dominio.User;

@ManagedBean
@SessionScoped
public class UserMBean {

	private User user;
	
	private User userLogged;
	
	private DataModel<User> usersModel;
	
	private boolean logged = false;
	
	@Inject
	private UserDao userDao;
	
	public UserMBean() {
		user = new User();
	}

	public String logar() {
		
		User userBd = userDao.searchLogin(user.getLogin());
		
		if(userBd != null) {
			//existe e senha está correta
		
			if(userBd.getPassword().equals(user.getPassword())){
				userLogged = userBd;
				this.logged = true;
				return "/views/index.jsf";
			//senha incorreta
			} 
			else {
				FacesMessage msg = new FacesMessage("Senha incorreta.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage("", msg);
				return null;
			}
		//não existe
		}
		else {
			FacesMessage msg = new FacesMessage("Usuário não encontrado.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
			return null;
		}
	}

	public String newUser(){
		user = new User();
		return "/views/user/form.jsf";
	}
	
	public String listUsers(){
		usersModel = new ListDataModel<User>(userDao.list());
		return "/views/user/list.jsf";
	}
	
	public String addUser(){
		userDao.save(user);
		user = new User();
		return "/pages/user/form.jsf";
	}
	
	public String editUser(){
		user = usersModel.getRowData();
		userDao.save(user);
		return "/views/user/form.jsf";
	}
	
	public String removeUser(){
		User userRemoved = usersModel.getRowData();
		userDao.remove(userRemoved);
		usersModel = new ListDataModel<User>(userDao.list());
		return "/views/user/list.jsf";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUserLogged() {
		return userLogged;
	}

	public void setUserLogged(User userLogged) {
		this.userLogged = userLogged;
	}

	public DataModel<User> getUsersModel() {
		if(usersModel == null){
			usersModel = new ListDataModel<User>(userDao.list());
		}
		return usersModel;
	}

	public void setUsersModel(DataModel<User> usersModel) {
		this.usersModel = usersModel;
	}

	public boolean isLogged() {
		return logged;
	}
	
}
