package org.javasimon.base.clock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

import org.javasimon.core.SimonClock;

/**
 * Clock that should measure nanoseconds of CPU usage time.
 * Uses {@link java.lang.management.ThreadMXBean#getCurrentThreadCpuTime()} internally.
 * This Clock checks if CPU time is supported and tries to enable it when supported.
 * <p/>
 * If it is disabled (for instance after the initialization), -1 should be returned,
 * see Javadoc for {@link ThreadMXBean#getCurrentThreadCpuTime()}).
 * If CPU time is not supported, -2 is returned.
 */
enum CpuClock implements SimonClock {

	INSTANCE;

	public static final int NOT_SUPPORTED_RETURN_VALUE = -2;

	private final ThreadMXBean threadMXBean;

	private final boolean unsupported;

	CpuClock() {
		threadMXBean = ManagementFactory.getThreadMXBean();
		unsupported = !threadMXBean.isCurrentThreadCpuTimeSupported();

		enable();
	}

	@Override
	public long nanoTime() {
		if (unsupported) return NOT_SUPPORTED_RETURN_VALUE;

		return threadMXBean.getCurrentThreadCpuTime();
	}

	@Override
	public long milliTime() {
		if (unsupported) return NOT_SUPPORTED_RETURN_VALUE;

		return TimeUnit.NANOSECONDS.toMillis(nanoTime());
	}

	/**
	 * Returns status of this clock - whether the underlying mechanism is supported and
	 * whether it is enabled right now.
	 */
	public Status status() {
		if (unsupported) return Status.NOT_SUPPORTED;

		return threadMXBean.isThreadCpuTimeEnabled()
			? Status.ENABLED
			: Status.DISABLED;
	}

	/** Enables thread CPU time measurment (if supported). */
	public void enable() {
		if (unsupported) return;

		threadMXBean.setThreadCpuTimeEnabled(true);
	}

	public enum Status {
		ENABLED, DISABLED, NOT_SUPPORTED
	}
}
