package org.javasimon.base

import org.javasimon.base.clock.TestClock
import org.javasimon.base.counter.Counter
import spock.lang.Specification

class CounterSpec extends Specification {

	def "Newly created counter has counter set to zero."() {
		when:
		def counter = new Counter()

		then:
		counter.data.counter == 0
	}

	def "Nxewly created counter has counter set to zero."() {
		when:
		def clock = new TestClock()
		def manager = new DummyManager(clock)
		def counter = new Counter(null, manager)

		then:
		counter.data.counter == 0
	}

}
