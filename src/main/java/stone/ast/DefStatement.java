package stone.ast;

import java.util.List;

import stone.env.Env;
import stone.parser.Function;

public class DefStatement extends ASTList {

	public DefStatement(List<ASTNode> children) {
		super(children);
	}

	public String name() {
		return ((ASTLeaf) child(0)).token().getText();
	}

	public ParameterList parameters() {
		return (ParameterList) child(1);
	}

	public BlockStatement body() {
		return (BlockStatement) child(2);
	}

	public String toString() {
		return "(def " + name() + " " + parameters() + " " + body() + ")";
	}
	
	public Object evaluate(Env env) {
		env.putNew(name(), new Function(parameters(), body(), env));
		return name();
	}
}
