package org.javasimon.base;

import org.javasimon.core.Simon;
import org.javasimon.core.SimonManager;
import org.javasimon.core.SimonSample;

/** Base implementation of Simon interface. */
public abstract class SimonBase<N> implements Simon<N> {

	/** Simon's name. */
	protected final N name;

	/** Owning manager of this Simon. */
	protected final SimonManager<N> manager;

	public SimonBase(N name, SimonManager<N> manager) {
		this.name = name;
		this.manager = manager;
	}

	@Override
	public final N name() {
		return name;
	}

	@Override
	public final SimonManager<N> manager() {
		return manager;
	}

	public abstract SimonSample sample();
}
