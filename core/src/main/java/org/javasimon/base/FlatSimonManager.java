package org.javasimon.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.javasimon.core.Simon;
import org.javasimon.core.SimonFactory;
import org.javasimon.core.SimonManager;

public class FlatSimonManager<N> implements SimonManager<N> {

	private Map<N, Simon<N>> simons = new ConcurrentHashMap<>();

	private Map<Class<? extends Simon>, SimonFactory<N, ?>> factories = new HashMap<>();

	@Override
	@SuppressWarnings("unchecked")
	public final <S extends Simon<N>> S simon(N name, Class<S> simonType) {
		S simon = (S) simons.get(name);
		if (simon == null) {
			synchronized (this) {
				return createSimon(name, simonType);
			}
		}
		return simon;
	}

	@SuppressWarnings("unchecked")
	private <S extends Simon<N>> S createSimon(N name, Class<S> simonType) {
		SimonFactory<N, S> factory = (SimonFactory<N, S>) factories.get(simonType);
		S simon = factory.create(name, this);
		simons.put(name, simon);
		return simon;
	}

	@Override
	public final Collection<N> simonNames() {
		return simons.keySet();
	}

	@Override
	public final synchronized <S extends Simon<N>> SimonManager<N> registerSimonType(
		SimonFactory<N, S> simonFactory)
	{
		factories.put(simonFactory.simonType(), simonFactory);
		return this;
	}
}
