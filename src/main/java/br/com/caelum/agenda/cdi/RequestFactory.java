package br.com.caelum.agenda.cdi;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.servlet.http.HttpServletRequest;

@RequestScoped
public class RequestFactory {

	private HttpServletRequest req;

	public void setRequest(@Observes HttpServletRequest req) {
		this.req = req;
	}
	
	@Produces
	public HttpServletRequest getRequest(){
		return req;
	}
}
