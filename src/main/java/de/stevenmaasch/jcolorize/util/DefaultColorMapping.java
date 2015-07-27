package de.stevenmaasch.jcolorize.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DefaultColorMapping {

	private final Map<Pattern, AnsiEscape> mapping;
	
	public static DefaultColorMapping getInstance() {
		return new DefaultColorMapping(getMappingFromAnnotation(MatchPattern.class));
	}
	
	public Map<Pattern, AnsiEscape> getMapping() {
		return mapping;
	}
	
	private DefaultColorMapping(Map<Pattern, AnsiEscape> mapping) {
		this.mapping = mapping;
	}
	
	private static Iterable<Field> getPublicStaticDeclaredFieldsByType(Class<?> c, Class<?> type) {
		return filterFieldsByType(type, getPublicStaticDeclaredFields(c));
	}
	
	private static boolean isPublicStaticField(Field field) {
		final int modifiers = field.getModifiers();
		return Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers);
	}
	
	private static Iterable<Field> getPublicStaticDeclaredFields(Class<?> c) {
		final List<Field> matchedFields = new ArrayList<>();
		for (Field field : c.getDeclaredFields()) {
			if (isPublicStaticField(field)) {
				matchedFields.add(field);
			}
		}
		return matchedFields;
	}
	
	private static Iterable<Field> filterFieldsByType(Class<?> type, Iterable<Field> fields) {
		final List<Field> matchedFields = new ArrayList<>();
		for (Field field : fields) {
			if (field.getType() == type) {
				matchedFields.add(field);
			}
		}
		return matchedFields;
	}
	
	private static Map<Pattern, AnsiEscape> getMappingFromAnnotation(Class<?> c) {
		final Map<Pattern, AnsiEscape> mapping = new HashMap<>();
		for (Field field : getPublicStaticDeclaredFieldsByType(c, Pattern.class)) {
			Colorize colorize = field.getAnnotation(Colorize.class);
			if (colorize != null && colorize.enabled()) {
				try {
					mapping.put((Pattern) field.get(null), colorize.value());
				} catch (IllegalArgumentException e) {
					throw new RuntimeException("Unable to generate color mapping from annotations.", e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Unable to generate color mapping from annotations.", e);
				}
			}
		}
		return mapping;
	}
	
}
