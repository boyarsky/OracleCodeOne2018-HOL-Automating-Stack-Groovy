/**
 *This file is the sample code against we run our unit test.
 *It is placed src/test/files in order to not be part of the maven compilation.
 **/
class SecretNotAllowedCheck {

	private int secret;  // Noncompliant
	private String other;
	
	public static final int SECRET_WORD = 5;  // Noncompliant
	public static final int OK = 3;
	
	public int methodWithSecretImplementation() {  // Noncompliant
		int secret = 42;  // Noncompliant
		int otherValue = 6;
		return secret;
	}

}
