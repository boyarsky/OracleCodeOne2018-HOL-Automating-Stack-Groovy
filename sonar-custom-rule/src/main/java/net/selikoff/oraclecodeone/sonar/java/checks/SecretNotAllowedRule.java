package net.selikoff.oraclecodeone.sonar.java.checks;

import org.sonar.check.*;
import org.sonar.plugins.java.api.*;
import org.sonar.plugins.java.api.tree.*;

@Rule(key = "SecretNotAllowed",
	  name = "'secret' should not be used",
	  description = "For methods and variables, do not use phrase 'secret'.",
	  priority = Priority.BLOCKER,
	  tags = {"bug"})
public class SecretNotAllowedRule extends BaseTreeVisitor implements JavaFileScanner {

	private JavaFileScannerContext context;

	// --------------------------------------------------

	boolean isSecret(String name) {
		return name.toLowerCase().contains("secret");
	}

	// --------------------------------------------------

	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;

		// The call to the scan method on the root of the tree triggers the visit of the
		// AST by this visitor
		scan(context.getTree());

		// For debugging purpose, you can print out the entire AST of the analyzed file
		System.out.println(PrinterVisitor.print(context.getTree())); // NOSONAR
	}

	// --------------------------------------------------

	@Override
	public void visitMethod(MethodTree tree) {

		if (isSecret(tree.simpleName().name())) {
			context.reportIssue(this, tree, "Avoid using 'secret' in source code");
		}
		super.visitMethod(tree);
	}

	// --------------------------------------------------

	@Override
	public void visitVariable(VariableTree tree) {
		if (isSecret(tree.simpleName().name())) {
			context.reportIssue(this, tree, "Avoid using 'secret' in source code");
		}
		super.visitVariable(tree);
	}

}