package stone.ast;

import java.util.List;

import stone.env.IEnv;

public class WhileStatement extends ASTList {
	public WhileStatement(List<ASTNode> children) {
		super(children);
	}

	public ASTNode condition() {
		return child(0);
	}

	public ASTNode body() {
		return child(1);
	}

	public String toString() {
		return "(while " + condition() + " " + body() + ")";
	}

	@Override
	public Object evaluate(IEnv env) {
		Object result = 0;
		for (;;) {
			Object cond = condition().evaluate(env);
			if (cond instanceof Boolean && !((Boolean) cond).booleanValue()) {
				return result;
			}
			result = body().evaluate(env);
		}
	}
}
