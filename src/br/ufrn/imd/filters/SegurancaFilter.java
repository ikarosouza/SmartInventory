package br.ufrn.imd.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrn.imd.controllers.UserMBean;

@WebFilter("/pages/*")
public class SegurancaFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		UserMBean usuarioMBean = (UserMBean) req.getSession().getAttribute("usuarioMBean");
		
		if (usuarioMBean == null || usuarioMBean.getUserLogged() == null) 
			res.sendRedirect("/SistemaCadastro/index.jsf");
		else 
			chain.doFilter(request, response);

	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

}