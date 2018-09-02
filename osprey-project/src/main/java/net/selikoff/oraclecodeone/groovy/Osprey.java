package net.selikoff.oraclecodeone.groovy;

import java.io.*;
import java.nio.charset.*;

import org.apache.commons.io.*;

public class Osprey {
	
	public int everything() throws IOException {
		// yes this can be one without a library
		// but trying to show a dependency!
		File file = new File("src/main/resources/answer.txt");
		String text = FileUtils.readFileToString(file, Charset.defaultCharset());
		return Integer.parseInt(text);
	}
}
