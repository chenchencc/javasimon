package org.javasimon.base;

import org.javasimon.core.Simon;

public class Counter<N> implements Simon<N> {

	private N name;

	public Counter(N name) {
		this.name = name;
	}

	@Override
	public N name() {
		return name;
	}
}
