package stone.ast;

import java.util.List;

import stone.env.Environment;

public abstract class Postfix extends ASTList {

	public Postfix(List<ASTNode> children) {
		super(children);
	}

	public abstract Object evaluate(Environment environment, Object value);

}
