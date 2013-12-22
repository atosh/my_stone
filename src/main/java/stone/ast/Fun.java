package stone.ast;

import java.util.List;

import stone.env.Env;
import stone.parser.Function;

public class Fun extends ASTList {

	public Fun(List<ASTNode> children) {
		super(children);
	}

	public ParameterList parameters() {
		return (ParameterList) child(0);
	}

	public BlockStatement body() {
		return (BlockStatement) child(1);
	}

	public String toString() {
		return "(fun " + parameters() + " " + body() + ")";
	}

	public Object evaluate(Env env) {
		return new Function(parameters(), body(), env);
	}

}