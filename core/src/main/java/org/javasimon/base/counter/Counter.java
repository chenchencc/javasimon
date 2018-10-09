package org.javasimon.base.counter;

import org.javasimon.base.DummyManager;
import org.javasimon.base.SimonBase;
import org.javasimon.core.Nullable;
import org.javasimon.core.SimonManager;

public class Counter<N> extends SimonBase<N> {

	public volatile CounterData data = new CounterData();

	public Counter() {
		this(null, DummyManager.getDefault());
	}

	public Counter(@Nullable N name, @Nullable SimonManager<N> manager) {
		super(name, manager);
	}

	@Override
	public CounterSample sample() {
		// TODO
		return new CounterSample(data, null);
	}
}
