package stone.ast;

import java.util.List;

import stone.env.IEnv;

public class ClassBody extends ASTList {

	public ClassBody(List<ASTNode> children) {
		super(children);
	}

	public Object evaluate(IEnv env) {
		for (ASTNode node : this) {
			node.evaluate(env);
		}
		return null;
	}
}
