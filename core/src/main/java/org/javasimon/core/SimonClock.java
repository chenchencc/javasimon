package org.javasimon.core;

/**
 * Represents clock returning current time in millis and nanos.
 * By default {@link SimonClock#SYSTEM} is used to match System timers, but different
 * implementation can be used to provide different time for {@link org.javasimon.core.SimonManager}.
 * This also allows easier testing without sleeping.
 *
 * <b>IMPORTANT NOTE:</b>
 * Depending on the implementation millis may or may not represent wall clock time.
 * Typically in {@link SimonClock#SYSTEM} clock millis are timestamp since 1970 (see
 * {@link System#currentTimeMillis()} for details, but nanos are not based on wall clock time.
 * In other implementations of SimonClock both millis and nanos can have different meaning.
 * Typically millis and nanos advance together but minor deviances can be observed in cases
 * where each is based on different timer.
 *
 * @noinspection UnusedDeclaration
 */
public interface SimonClock {

	/**
	 * Gets current time in nanoseconds.
	 *
	 * @return current time in nanoseconds
	 */
	long nanoTime();

	/**
	 * Gets current time in milliseconds.
	 *
	 * @return current time in milliseconds
	 */
	long milliTime();

	/**
	 * Default implementation using {@link java.lang.System#currentTimeMillis()}
	 * and {@link java.lang.System#nanoTime()}.
	 */
	SimonClock SYSTEM = new SimonClock() {
		@Override
		public long nanoTime() {
			return System.nanoTime();
		}

		@Override
		public long milliTime() {
			return System.currentTimeMillis();
		}
	};
}