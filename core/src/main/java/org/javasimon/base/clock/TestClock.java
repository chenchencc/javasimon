package org.javasimon.base.clock;

import java.util.concurrent.TimeUnit;

import org.javasimon.core.SimonClock;

/**
 * TestClock allows setting any arbitrary values for millis and nanos.
 * Available for non-test code too, if for nothing else then it's easier to use in tests
 * in other modules too.
 * <p>
 * When normal setters are used both millis and nanos have the same 0 base and millis are
 * always one millionth of nanos.
 * This invariant can be broken using {@code set*Only} methods.
 *
 * @noinspection unused
 */
public final class TestClock implements SimonClock {

	private long millis;
	private long nanos;

	@Override
	public long nanoTime() {
		return nanos;
	}

	@Override
	public long milliTime() {
		return millis;
	}

	/** Sets millis and synchronizes nanos with it. */
	public void setMillis(long millis) {
		this.millis = millis;
		this.nanos = TimeUnit.MILLISECONDS.toNanos(millis);
	}

	/** Sets nanos and synchronizes millis with it. */
	public void setNanos(long nanos) {
		this.nanos = nanos;
		this.millis = TimeUnit.NANOSECONDS.toMillis(nanos);
	}

	/** Sets millis without touching nanos. */
	public void setMillisOnly(long millis) {
		this.millis = millis;
	}

	/** Sets nanos without touching millis. */
	public void setNanosOnly(long nanos) {
		this.nanos = nanos;
	}

	public static void main(String[] args) {
		if (args.length > 5) {
			CpuClock instance = CpuClock.INSTANCE;
		}
		System.out.println("NOW");
	}
}
