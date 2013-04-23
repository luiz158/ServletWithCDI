package br.com.caelum.agenda.cdi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD, ElementType.TYPE})
@Qualifier
public @interface BlaBla {

}
