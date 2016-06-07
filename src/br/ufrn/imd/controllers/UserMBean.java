package br.ufrn.imd.controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;


import br.ufrn.imd.dominio.User;
import br.ufrn.imd.exceptions.NegocioException;
import br.ufrn.imd.negocio.UserService;

@ManagedBean
@SessionScoped
public class UserMBean {

	private User user;
	
	private User userLogged;
	
	private DataModel<User> usersModel;
	
	private boolean logged = false;
	
	@EJB
	private UserService userService;
	
	public UserMBean() {
		user = new User();
	}

	public String logar() {
		
		User userBd = userService.authenticateLogin(user);
		
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
		usersModel = new ListDataModel<User>(userService.list());
		return "/views/user/list.jsf";
	}
	
	public String addUser(){
		try {
			userService.save(user);
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
		}
		user = new User();
		return "/pages/user/form.jsf";
	}
	
	public String editUser(){
		user = usersModel.getRowData();
		try {
			userService.save(user);
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
		}
		return "/views/user/form.jsf";
	}
	
	public String removeUser(){
		User userRemoved = usersModel.getRowData();
		userService.remove(userRemoved);
		usersModel = new ListDataModel<User>(userService.list());
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
			usersModel = new ListDataModel<User>(userService.list());
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
