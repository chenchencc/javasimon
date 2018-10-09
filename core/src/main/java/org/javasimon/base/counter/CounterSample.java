package org.javasimon.base.counter;

import org.javasimon.base.SimonUsages;
import org.javasimon.core.SimonSample;

public class CounterSample implements SimonSample {

	public final CounterData counterData;
	public final SimonUsages simonUsages;

	public CounterSample(CounterData counterData, SimonUsages simonUsages) {
		this.counterData = counterData;
		this.simonUsages = simonUsages;
	}

	@Override
	public SimonUsages usages() {
		return simonUsages;
	}
}
