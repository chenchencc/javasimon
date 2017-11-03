package org.javasimon.base.counter;

import org.javasimon.base.SimonBase;
import org.javasimon.core.Nullable;
import org.javasimon.core.SimonManager;

public class Counter<N> extends SimonBase<N> {

	public volatile CounterData data = new CounterData();

	public Counter() {
		this(null, null);
	}

	public Counter(@Nullable N name, @Nullable SimonManager<N> manager) {
		super(name, manager);
	}
}
