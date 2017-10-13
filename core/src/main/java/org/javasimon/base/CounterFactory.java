package org.javasimon.base;

import org.javasimon.core.SimonFactory;
import org.javasimon.core.SimonManager;

public class CounterFactory<N> implements SimonFactory<N, Counter<N>> {

	@Override
	public Counter<N> create(N simonName, SimonManager<N> simonManager) {
		return new Counter<>(simonName);
	}

	@Override
	public Class<Counter> simonType() {
		return Counter.class;
	}
}
