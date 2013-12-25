package stone.ast;

import java.util.List;

import stone.env.IEnv;

public abstract class Postfix extends ASTList {

	public Postfix(List<ASTNode> children) {
		super(children);
	}

	public abstract Object evaluate(IEnv env, Object value);

}
