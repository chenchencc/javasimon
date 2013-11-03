package org.javasimon.examples.testapp;

import org.javasimon.jmx.SimonManagerMXBeanImpl;
import org.javasimon.examples.testapp.test.Runner;
import org.javasimon.examples.testapp.mm.AppMXBean;
import org.javasimon.jmx.SimonManagerMXBean;
import org.javasimon.jdbc4.jmx.JdbcMXBean;
import org.javasimon.jdbc4.jmx.JdbcMXBeanImpl;
import org.javasimon.SimonManager;
import org.h2.tools.RunScript;

import javax.management.ObjectName;
import javax.management.MBeanServer;
import javax.management.JMException;
import java.lang.management.ManagementFactory;
import java.sql.DriverManager;
import java.sql.Connection;

/**
 * Main class of the test application (DB+JMX).
 * Run it from main javasimon directory (where {@code examples} directory is). Compilation:
 * <pre>javac -cp lib/h2.jar:build/core:build/jdbc4:build/jmx examples/org/javasimon/testapp/*.java examples/org/javasimon/testapp/*\/*.java</pre>
 * Run it with command:
 * <pre>java -cp ../lib/h2.jar:build/core:build/jdbc4:ild/jmx:examples org.javasimon.examples.testapp.Main</pre>
 *
 * @author Radovan Sninsky
 * @since 2.0
 */
public class Main {

	private Runner runner;
	private Connection connection;

	/**
	 * Implementation of the application MX bean.
	 */
	public class AppMXBeanImpl implements AppMXBean {
		/**
		 * Shutdown operation.
		 */
		public void shutdown() {
			if (runner != null) {
				runner.stop();
			}
		}
	}

	private void setupDatabase() throws Exception {
		RunScript.execute("jdbc:h2:file:testappdb", "sa", "sa", "examples/testapp.db.sql", null, false);
		connection = DriverManager.getConnection("jdbc:simon:h2:file:testappdb;simon_prefix=org.javasimon.examples.testapp.jdbc", "sa", "sa");
	}

	private void closeDatabase() throws Exception {
		connection.close();
	}

	private void setupRunner() {
		WeightController controller = new WeightController();
		controller.addAction(new InsertAction(new RandomNumberDataProvider(700), connection), 34);
		controller.addAction(new InsertBatchAction(connection), 12);
		controller.addAction(new UpdateAction(new RandomNumberDataProvider(400), connection), 28);
		controller.addAction(new DeleteAction(new RandomNumberDataProvider(600), connection), 26);

		UniformRandomTimer timer = new UniformRandomTimer(7100, 900);

		runner = new Runner(controller, timer);
	}

	private void setupJmx() {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		try {
			AppMXBean app = new AppMXBeanImpl();
			mbs.registerMBean(app, new ObjectName("org.javasimon.examples.testapp:type=App"));
			System.out.println("AppMXBean registerd");

			SimonManagerMXBean simonManagerMXBean = new SimonManagerMXBeanImpl(SimonManager.manager());
			mbs.registerMBean(simonManagerMXBean, new ObjectName("org.javasimon.examples.testapp:type=Simon"));
			System.out.println("SimonManagerMXBean registerd");

			JdbcMXBean jdbc = new JdbcMXBeanImpl(SimonManager.manager(), "org.javasimon.examples.testapp.jdbc");
			mbs.registerMBean(jdbc, new ObjectName("org.javasimon.examples.testapp:type=Jdbc"));
			System.out.println("JdbcMXBean registerd");
		} catch (JMException e) {
			System.out.println("JMX beans registration failed!\n"+e);
		}
	}

	private void run() {
		runner.run();
	}

	/**
	 * Entry point of the test application.
	 *
	 * @param args unused
	 * @throws Exception well, it's just an example!
	 */
	public static void main(String[] args) throws Exception {
		Main m = new Main();
		m.setupDatabase();
		m.setupRunner();
		m.setupJmx();

		m.run();

		m.closeDatabase();
	}
}