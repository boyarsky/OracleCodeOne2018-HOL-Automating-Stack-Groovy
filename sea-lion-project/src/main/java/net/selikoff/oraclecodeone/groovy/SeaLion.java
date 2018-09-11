package net.selikoff.oraclecodeone.groovy;

import java.io.*;
import java.nio.charset.*;

import org.apache.commons.io.*;

public class SeaLion {

	// the sea lions have lots of secrets!
	public String secretValue;
	
	public String everything() throws IOException {
		// yes this can be one without a library
		// but trying to show a dependency!
		File file = new File("src/main/resources/answer.txt");
		return FileUtils.readFileToString(file, Charset.defaultCharset());
	}
}
