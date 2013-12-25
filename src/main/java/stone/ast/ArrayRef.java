package stone.ast;

import java.util.List;

import stone.env.IEnv;
import stone.lexer.StoneException;

public class ArrayRef extends Postfix {
	public ArrayRef(List<ASTNode> children) {
		super(children);
	}

	public ASTNode index() {
		return child(0);
	}

	public String toString() {
		return "[" + index() + "]";
	}

	@Override
	public Object evaluate(IEnv env, Object value) {
		if (value instanceof Object[]) {
			Object index = index().evaluate(env);
			if (index instanceof Integer) {
				return ((Object[]) value)[(int) index];
			}
		}
		throw new StoneException("bad array access.", this);
	}
}
