package br.com.caelum.agenda.cdi.extension;

import java.util.Set;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.inject.spi.ProcessInjectionTarget;

import br.com.caelum.agenda.cdi.annotation.ParamBean;

public class ParamBeanExtension implements Extension {

	public <T> void processInjectionTarget(@Observes ProcessInjectionTarget<T> pit, BeanManager beanManager) {
//		System.out.println("Processing classes: " + pit.getAnnotatedType());
		Set<InjectionPoint> injectionPoints = pit.getInjectionTarget().getInjectionPoints();
		InjectionTarget<T> it = pit.getInjectionTarget();

		if (pit.getAnnotatedType().isAnnotationPresent(ParamBean.class)) {
			
			BeanManagerUtil util = new BeanManagerUtil(beanManager);

			InjectionTarget<T> wrapped = new InjectionTargetRequestReader<T>(it, util);
			pit.setInjectionTarget(wrapped);
		}
	}
}
