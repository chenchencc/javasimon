package org.javasimon.base

import spock.lang.Specification;

class FlatSimonManagerSpec extends Specification {

	def "newly created manager has no simons"() {
		when:
		def manager = new FlatSimonManager()

		then:
		manager.simonNames() isEmpty()
	}


	def "after adding a simon its name is returned by simonNames"() {
		given:
		def manager = new FlatSimonManager<String>()
			.registerSimonType(new CounterFactory<>())

		when:
		manager.simon('simon', Counter.class)

		then:
		manager.simonNames() containsAll(['simon'])
	}
}
