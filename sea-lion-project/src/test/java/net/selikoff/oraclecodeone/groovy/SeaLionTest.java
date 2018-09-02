package net.selikoff.oraclecodeone.groovy;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.*;

class SeaLionTest {
	
	private SeaLion seaLion;
	
	@BeforeEach
	void setup() {
		seaLion = new SeaLion();
	}

	@Test
	void test() throws IOException {
		String expected = "super secret algorithm";
		assertEquals(expected, seaLion.everything(), "the answer!");
	}

}
