package de.stevenmaasch.jcolorize.util;

import java.util.Comparator;

public class MappingPositionComperator implements Comparator<Mapping> {

	@Override
	public int compare(Mapping m1, Mapping m2) {
		return Integer.compare(m1.getPosition(), m2.getPosition());
	}

}
