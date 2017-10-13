package org.javasimon.core;

/**
 * Generic type for all Simons, that is monitors that actually measure something.
 *
 * @param <N> type of a Simon name
 */
public interface Simon<N> {

	/** Returns Simon's name. */
	N name();
}
