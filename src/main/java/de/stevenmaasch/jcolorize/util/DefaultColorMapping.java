package de.stevenmaasch.jcolorize.util;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class DefaultColorMapping {

	private final List<Mapping> mapping;
	
	public static DefaultColorMapping getInstance() {
		final List<Mapping> mapping = getMappingFromAnnotation(MatchPattern.class);
		Collections.sort(mapping, new MappingPositionComperator());
		return new DefaultColorMapping(mapping);
	}
	
	public List<Mapping> getMapping() {
		return mapping;
	}
	
	private DefaultColorMapping(List<Mapping> mapping) {
		this.mapping = mapping;
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
	
	private static List<Mapping> getMappingFromAnnotation(Class<?> c) {
		final List<Mapping> mapping = new LinkedList<>();
		for (Field field : getPublicStaticDeclaredFieldsByType(c, Pattern.class)) {
			Colorize colorize = field.getAnnotation(Colorize.class);
			if (colorize != null && colorize.enabled()) {
				try {
					mapping.add(new Mapping((Pattern) field.get(null), colorize.value(), colorize.position()));
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
