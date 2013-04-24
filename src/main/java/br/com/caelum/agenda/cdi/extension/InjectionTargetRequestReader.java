package br.com.caelum.agenda.cdi.extension;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;

import br.com.caelum.agenda.cdi.RequestFactory;

class InjectionTargetRequestReader<T> implements InjectionTarget<T> {

	private final InjectionTarget<T> it;
	private final BeanManagerUtil util;
	
	InjectionTargetRequestReader(InjectionTarget<T> it, BeanManagerUtil util) {
		this.it = it;
		this.util = util;
	}

	@Override
	public void inject(T instance, CreationalContext<T> ctx) {

		System.out.println("creating a instance of the object: " + instance);
		it.inject(instance, ctx);

		RequestFactory requests = util.instanceFor(RequestFactory.class);
		Map<String, String[]> parameters = requests.getRequest().getParameterMap();

		try {
			Class<?> clazz = instance.getClass();
			for (Entry<String, String[]> entries : parameters.entrySet()) {
				String paramName = entries.getKey();
				String paramValue = entries.getValue()[0];

				Field field = clazz.getDeclaredField(paramName);
				field.setAccessible(true);
				if(field.getType().isAssignableFrom(paramValue.getClass())){
					field.set(instance, paramValue);
				}

				System.out.println("K: " + paramName);
				System.out.println("V: " + Arrays.toString(entries.getValue()));
				System.out.println("----------------------------");
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void postConstruct(T instance) {
		it.postConstruct(instance);
	}

	@Override
	public void preDestroy(T instance) {
		it.preDestroy(instance);
	}

	@Override
	public void dispose(T instance) {
		it.dispose(instance);
	}

	@Override
	public Set<InjectionPoint> getInjectionPoints() {
		return it.getInjectionPoints();
	}

	@Override
	public T produce(CreationalContext<T> ctx) {
		return it.produce(ctx);
	}
}