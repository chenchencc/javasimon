package org.javasimon.core;

import java.util.Collection;

/**
 * Simon manager using names of type {@link N}.
 *
 * @param <N> type of Simon names
 */
public interface SimonManager<N> {

	/**
	 * Returns Simon by its name. TODO should it ever return null? does this also create?
	 *
	 * @param name name of the Simon
	 * @return Simon object
	 */
	<S extends Simon<N>> S simon(N name, Class<S> simonType);

	/**
	 * Returns unmodifiable collection containing names of all existing Simons. Collection is not
	 * ordered.
	 *
	 * @return collection of all Simon names
	 */
	Collection<N> simonNames();

	<S extends Simon<N>> SimonManager<N> registerSimonType(SimonFactory<N, S> simonFactory);
}
