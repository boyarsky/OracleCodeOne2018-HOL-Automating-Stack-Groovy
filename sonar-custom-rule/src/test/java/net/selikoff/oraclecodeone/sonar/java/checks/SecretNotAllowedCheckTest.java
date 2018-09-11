package net.selikoff.oraclecodeone.sonar.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class SecretNotAllowedCheckTest {

	@Test
	public void detected() {
		JavaCheckVerifier.verify("src/test/files/SecretNotAllowedCheck.java", new SecretNotAllowedRule());
	}
}
