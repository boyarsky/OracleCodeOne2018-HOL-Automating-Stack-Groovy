package net.selikoff.oraclecodeone.groovy;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.*;

class OspreyTest {
	
	private Osprey osprey;
	
	@BeforeEach
	void setup() {
		osprey = new Osprey();
	}

	@Test
	void test() throws IOException {
		assertEquals(42, osprey.everything(), "the answer!");
	}

}
