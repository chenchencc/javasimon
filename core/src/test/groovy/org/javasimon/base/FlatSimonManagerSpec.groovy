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
			.registerSimonFactory(new CounterFactory<>())

		when:
		manager.simon('simon', Counter.class)

		then:
		manager.simonNames() containsAll(['simon'])
	}

	def "After destroying a Simon its name is not returned by simonNames."() {
		given:
		def manager = new FlatSimonManager<String>()
			.registerSimonFactory(new CounterFactory<>())
		manager.simon('simon', Counter.class)

		when:
		manager.destroySimon('simon')

		then:
		manager.simonNames() isEmpty()
	}
}
