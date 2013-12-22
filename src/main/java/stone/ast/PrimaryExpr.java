package stone.ast;

import java.util.List;

import stone.ast.ASTList;
import stone.env.Env;

public class PrimaryExpr extends ASTList {

	public PrimaryExpr(List<ASTNode> children) {
		super(children);
	}

	public static ASTNode create(List<ASTNode> children) {
		return children.size() == 1 ? children.get(0) : new PrimaryExpr(
				children);
	}

	public ASTNode operand() {
		return child(0);
	}

	public Object evaluate(Env env) {
		return evaluateSubExpr(env, 0);
	}

	public Object evaluateSubExpr(Env env, int nest) {
		if (hasPostfix(nest)) {
			Object target = evaluateSubExpr(env, nest + 1);
			return postfix(nest).evaluate(env, target);
		}
		return operand().evaluate(env);
	}

	public Postfix postfix(int nest) {
		return (Postfix) child(numChildren() - nest - 1);
	}

	public boolean hasPostfix(int nest) {
		return numChildren() - nest > 1;
	}
}
