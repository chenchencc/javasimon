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
	 * Returns unmodifiable collection containing names of all existing Simons.
	 * Collection is not ordered.
	 *
	 * @return collection of all Simon names
	 */
	Collection<N> simonNames();

	/**
	 * Destroys Simon specified by name.
	 *
	 * @param name name of the Simon to be destroyed
	 */
	void destroySimon(N name);

	/**
	 * Registers new type of {@link SimonFactory} with this Manager to support new Simon type.
	 *
	 * @param simonFactory factory that recognizes the type and knows how to create Simons
	 * @param <S> type of Simon
	 * @return this Manager
	 */
	<S extends Simon<N>> SimonManager<N> registerSimonFactory(SimonFactory<N, S> simonFactory);

	/**
	 * Returns {@link SimonClock} this manager is using.
	 * This should not change during the lifetime of the manager to prevent inconsistent results.
	 *
	 * @return simon clock this manager is using
	 */
	SimonClock clock();
}
