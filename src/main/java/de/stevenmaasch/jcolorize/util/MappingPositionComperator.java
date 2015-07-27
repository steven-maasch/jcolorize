package de.stevenmaasch.jcolorize.util;

import java.io.Serializable;
import java.util.Comparator;

public class MappingPositionComperator implements Comparator<Mapping>, Serializable {

	private static final long serialVersionUID = -5109372544055349531L;

	@Override
	public int compare(Mapping m1, Mapping m2) {
		return Integer.compare(m1.getPosition(), m2.getPosition());
	}

}
