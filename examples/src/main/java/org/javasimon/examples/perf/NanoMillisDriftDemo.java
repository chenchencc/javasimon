package org.javasimon.examples.perf;

/**
 * NanoMillisDriftDemo.
 */
public class NanoMillisDriftDemo {

	public static void main(String[] args) throws InterruptedException {
		long millisBase = System.currentTimeMillis();
		long nanosBase = System.nanoTime();
		System.out.println("Starting with millis " + millisBase + " and nanos " + nanosBase);
		System.out.println("\nΔ-millis  Δ-nanos   nano-milli drift in ms");

		while (true) {
			Thread.yield();
			long millisDelta = System.currentTimeMillis() - millisBase;
			long nanosDelta = System.nanoTime() - nanosBase;
			long nanosDeltaInMillis = nanosDelta / 1_000_000;
			System.out.printf("%-8d  %-8d  %d\n",
				millisDelta, nanosDeltaInMillis, nanosDeltaInMillis - millisDelta);

			Thread.sleep(10000);
			Thread.yield();
		}
	}
}
