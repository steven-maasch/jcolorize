package de.stevenmaasch.jcolorize.util;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class ColorMapping {

	private final List<Mapping> mapping;
	
	public static ColorMapping getInstance() {
		final List<Mapping> mapping = new LinkedList<>();
		for (Field field : getPublicStaticDeclaredFieldsByType(MatchPattern.class, Pattern.class)) {
			Colorize colorize = field.getAnnotation(Colorize.class);
			if (colorize != null && colorize.enabled()) {
				try {
					mapping.add(new Mapping((Pattern) field.get(null), colorize.value(), colorize.position()));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return new ColorMapping(mapping);
	}
	
	public List<Mapping> getMapping() {
		return mapping;
	}
	
	private ColorMapping(List<Mapping> mapping) {
		this.mapping = mapping;
		Collections.sort(this.mapping, new MappingPositionComperator());
	}
	
	private static Iterable<Field> getPublicStaticDeclaredFieldsByType(Class<?> c, Class<?> type) {
		return filterFieldsByType(type, getPublicStaticDeclaredFields(c));
	}
	
	private static boolean isPublicStatic(Member member) {
		final int modifiers = member.getModifiers();
		return Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers);
	}
	
	private static Iterable<Field> getPublicStaticDeclaredFields(Class<?> c) {
		final List<Field> matchedFields = new ArrayList<>();
		for (Field field : c.getDeclaredFields()) {
			if (isPublicStatic(field)) {
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
