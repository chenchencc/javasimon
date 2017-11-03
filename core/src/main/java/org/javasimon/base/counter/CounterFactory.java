package org.javasimon.base.counter;

import org.javasimon.core.SimonFactory;
import org.javasimon.core.SimonManager;

public class CounterFactory<N> implements SimonFactory<N, Counter<N>> {

	@Override
	public Counter<N> create(N simonName, SimonManager<N> simonManager) {
		return new Counter<>(simonName, simonManager);
	}

	@Override
	public Class<Counter> simonType() {
		return Counter.class;
	}
}
