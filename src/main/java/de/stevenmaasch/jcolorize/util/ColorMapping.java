package de.stevenmaasch.jcolorize.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class ColorMapping {

	private final Iterable<Pair<Pattern, AnsiEscape>> mapping;
	
	public static ColorMapping getInstance() {
		final ArrayList<Pair<Pattern, AnsiEscape>> mappings = new ArrayList<Pair<Pattern, AnsiEscape>>();
		for (Field field : getPublicStaticDeclaredFieldsByType(MatchPattern.class, Pattern.class)) {
			Colorize colorize = field.getAnnotation(Colorize.class);
			if (colorize != null && colorize.enabled()) {
				try {
					mappings.add(new ImmutablePair<Pattern, AnsiEscape>(((Pattern) field.get(null)), colorize.value()));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return new ColorMapping(mappings);
	}
	
	public Iterable<Pair<Pattern, AnsiEscape>> getMapping() {
		return mapping;
	}
	
	private ColorMapping(Iterable<Pair<Pattern, AnsiEscape>> mapping) {
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
	
}
