package org.javasimon.core;

/**
 * Factory for Simon of type {@link S}.
 *
 * @param <N> type of Simon name (this typically stays as parametrized type)
 * @param <S> type of a Simon, typically specified by the factory for a concrete Simon type
 */
public interface SimonFactory<N, S extends Simon<N>> {

	S create(N simonName, SimonManager<N> simonManager);

	Class<? extends Simon> simonType();
}
