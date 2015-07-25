package de.stevenmaasch.jcolorize.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Colorize {
	
	AnsiEscape value();
	
	boolean enabled() default true;

	int position() default 0;
	
}
