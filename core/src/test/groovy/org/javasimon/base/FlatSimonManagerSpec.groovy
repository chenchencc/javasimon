package org.javasimon.base

import org.javasimon.base.counter.Counter
import org.javasimon.base.counter.CounterFactory
import spock.lang.Specification

class FlatSimonManagerSpec extends Specification {

	def "Newly created manager has no Simons."() {
		when:
		def manager = new FlatSimonManager()

		then:
		manager.simonNames() isEmpty()
	}


	def "After adding a Simon its name is returned by simonNames."() {
		given:
		def manager = new FlatSimonManager<String>()
			.registerSimonType(new CounterFactory<>())

		when:
		manager.simon('simon', Counter.class)

		then:
		manager.simonNames() containsAll(['simon'])
	}
}
