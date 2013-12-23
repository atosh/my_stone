package stone.ast;

import java.util.List;

import stone.env.IEnv;

public class ArrayLiteral extends ASTList {

	public ArrayLiteral(List<ASTNode> children) {
		super(children);
	}

	public int size() {
		return numChildren();
	}

	public Object evaluate(IEnv env) {
		int s = numChildren();
		Object[] results = new Object[s];
		int i = 0;
		for (ASTNode node : this) {
			results[i++] = node.evaluate(env);
		}
		return results;
	}
}
