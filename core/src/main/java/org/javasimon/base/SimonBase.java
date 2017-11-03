package org.javasimon.base;

import org.javasimon.core.Simon;
import org.javasimon.core.SimonManager;

/** Base implementation of Simon interface. */
public class SimonBase<N> implements Simon<N> {

	/** Simon's name. */
	protected final N name;

	/** Owning manager of this Simon. */
	protected final SimonManager<N> manager;

	/** Creates non-managed Simon without a name, not owned by any manager. */
	public SimonBase() {
		name = null;
		manager = null;
	}

	public SimonBase(N name, SimonManager<N> manager) {
		this.name = name;
		this.manager = manager;
	}

	@Override
	public N name() {
		return name;
	}

	@Override
	public SimonManager<N> manager() {
		return manager;
	}
}
