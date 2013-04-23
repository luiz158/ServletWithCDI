package br.com.caelum.agenda.cdi;

import java.io.IOException;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(value="/*")
public class PegaCoisasFilter implements Filter {
	
	@Inject
	private Event<HttpServletRequest> evento;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		
		System.out.println("pegando request!!");
		HttpServletRequest req = (HttpServletRequest) request;
		evento.fire(req);
		
		chain.doFilter(req, response);
	}

	@Override
	public void destroy() {
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
