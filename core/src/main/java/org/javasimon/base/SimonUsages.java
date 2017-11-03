package org.javasimon.base;

public class SimonUsages {

	/** Timestamp of the first usage. */
	public final long firstUsage;

	/** Timestamp of the last usage. */
	public final long lastUsage;

	public SimonUsages(long firstUsage, long lastUsage) {
		this.firstUsage = firstUsage;
		this.lastUsage = lastUsage;
	}
}
