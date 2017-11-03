package org.javasimon.base.counter;

public class CounterData {

	/** An internal counter. */
	public final long counter;

	/** Sum of all increments. */
	public final long incrementSum;

	/** Sum of all decrements. */
	public final long decrementSum;

	/** A maximum tracker. */
	public final long max;

	public final long maxTimestamp;

	/** A minimum tracker - only negative values. */
	public final long min;

	public final long minTimestamp;

	public CounterData() {
		this(0, 0, 0, 0, Long.MIN_VALUE, 0, Long.MAX_VALUE);
	}

	private CounterData(long counter, long incrementSum, long decrementSum,
		long max, long maxTimestamp, long min, long minTimestamp)
	{
		this.counter = counter;
		this.incrementSum = incrementSum;
		this.decrementSum = decrementSum;
		this.max = max;
		this.maxTimestamp = maxTimestamp;
		this.min = min;
		this.minTimestamp = minTimestamp;
	}
}
