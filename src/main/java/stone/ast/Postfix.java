package stone.ast;

import java.util.List;

import stone.env.Env;

public abstract class Postfix extends ASTList {

	public Postfix(List<ASTNode> children) {
		super(children);
	}

	public abstract Object evaluate(Env env, Object value);

}
