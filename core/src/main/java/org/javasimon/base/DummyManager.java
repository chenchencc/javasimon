package org.javasimon.base;

import java.util.Collection;

import org.javasimon.core.Simon;
import org.javasimon.core.SimonClock;
import org.javasimon.core.SimonFactory;
import org.javasimon.core.SimonManager;

/**
 * Simon manager used when no real manager is needed, like for anonymous/unmanaged Simons.
 * This way clock is provided and internal null checks are not necessary.
 */
public class DummyManager<N> implements SimonManager<N> {

	/** See {@link #getDefault()} for type-safe return. */
	private static final DummyManager DEFAULT = new DummyManager<>(SimonClock.SYSTEM);

	private final SimonClock clock;

	public DummyManager(SimonClock clock) {
		this.clock = clock;
	}

	@Override
	public <S extends Simon<N>> S simon(N name, Class<S> simonType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<N> simonNames() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void destroySimon(N name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends Simon<N>> SimonManager<N> registerSimonFactory(SimonFactory<N, S> simonFactory) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SimonClock clock() {
		return clock;
	}

	@SuppressWarnings("unchecked")
	public static <R> DummyManager<R> getDefault() {
		return DEFAULT;
	}
}
