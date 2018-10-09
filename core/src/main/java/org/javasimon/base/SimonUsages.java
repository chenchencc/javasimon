package org.javasimon.base;

import org.javasimon.core.SimonClock;

/**
 * Represents various millisecond timestamps when Simon was created or used.
 * Value {@value #NO_VALUE} means that it was not set yet.
 * Object is immutable.
 * To apply a new timestamp call {@link #newUsage(long)} which returns new version of the object.
 */
public class SimonUsages {

	public static final long NO_VALUE = 0;

	/** Timestamp when the Simon was created. */
	public final long created;

	/** Timestamp of the first usage. */
	public final long firstUsage;

	/** Timestamp of the last usage. */
	public final long lastUsage;

	private SimonUsages(long created, long firstUsage, long lastUsage) {
		this.created = created;
		this.firstUsage = firstUsage;
		this.lastUsage = lastUsage;
	}

	/** Creates usages with specified created timestamp. */
	public SimonUsages(long created) {
		this(created, NO_VALUE, NO_VALUE);
	}

	/** Creates usages based on the timestamp taken from provided {@link SimonClock}. */
	public SimonUsages(SimonClock clock) {
		this(clock.milliTime());
	}

	/** Creates new usage object for provided usage timestamp. */
	public SimonUsages newUsage(long usageTimestamp) {
		long newLastUsage = Math.max(usageTimestamp, lastUsage);
		return new SimonUsages(created,
			firstUsage != NO_VALUE ? firstUsage : newLastUsage,
			newLastUsage);
	}
}
