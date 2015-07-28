package de.stevenmaasch.jcolorize.util;

import java.io.Serializable;
import java.util.Comparator;

import de.stevenmaasch.jcolorize.model.Mapping;

@SuppressWarnings("serial")
public class MappingPositionComperator implements Comparator<Mapping>, Serializable {

	@Override
	public int compare(Mapping m1, Mapping m2) {
		return Integer.compare(m1.getPosition(), m2.getPosition());
	}

}
