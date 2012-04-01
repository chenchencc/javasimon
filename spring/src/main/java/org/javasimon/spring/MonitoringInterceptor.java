package org.javasimon.spring;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.javasimon.Manager;
import org.javasimon.Split;
import org.javasimon.Stopwatch;
import org.javasimon.source.MonitorSource;
import org.javasimon.source.StopwatchTemplate;

/**
 * Method interceptor that measures the duration of the intercepted call with a Stopwatch.
 *
 * @author Erik van Oosten
 */
public final class MonitoringInterceptor implements MethodInterceptor, Serializable {
	/**
	 * Stopwatch template.
	 */
	private final StopwatchTemplate<MethodInvocation> stopwatchTemplate;

	/**
	 * Constructor
	 *
	 * @param stopwatchSource Provider stopwatch for method invocation
	 */
	public MonitoringInterceptor(MonitorSource<MethodInvocation, Stopwatch> stopwatchSource) {
		this.stopwatchTemplate = new StopwatchTemplate<MethodInvocation>(stopwatchSource);
	}

	/**
	 * Constuctor with Manager
	 * Monitors only methods and classes annotated with @Monitored
	 */
	public MonitoringInterceptor(Manager manager) {
		this(new SpringStopwatchSource(manager).cache());
	}

	/**
	 * Default constuctor.
	 * Monitors only methods and classes annotated with @Monitored
	 */
	public MonitoringInterceptor() {
		this(new SpringStopwatchSource().cache());
	}

	/**
	 * Performs method invocation and wraps it with Stopwatch.
	 *
	 * @param invocation method invocation
	 * @return return object from the method
	 * @throws Throwable anything thrown by the method
	 */
	public Object invoke(MethodInvocation invocation) throws Throwable {
		final Split split = stopwatchTemplate.start(invocation);
		try {
			return invocation.proceed();
		} finally {
			stopwatchTemplate.stop(split);
		}
	}
}