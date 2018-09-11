package net.selikoff.oraclecodeone.sonar.java.checks;

import static org.junit.Assert.*;

import org.junit.*;

public class SecretNotAllowedRuleTest {
	
	private SecretNotAllowedRule rule;
	
	@Before
	public void setUp() {
		rule = new SecretNotAllowedRule();
	}

	@Test
	public void notSecret() {
		assertFalse(rule.isSecret("other"));
	}
	
	@Test
	public void lowercase() {
		assertTrue(rule.isSecret("secret"));
	}
	
	@Test
	public void uppercase() {
		assertTrue(rule.isSecret("SECRET"));
	}
	
	@Test
	public void camelcase() {
		assertTrue(rule.isSecret("isSecretName"));
	}

}
