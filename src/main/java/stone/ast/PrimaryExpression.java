package stone.ast;

import java.util.List;

import stone.ast.ASTList;
import stone.env.Environment;

public class PrimaryExpression extends ASTList {

	public PrimaryExpression(List<ASTNode> children) {
		super(children);
	}

	public static ASTNode create(List<ASTNode> children) {
		return children.size() == 1 ? children.get(0) : new PrimaryExpression(
				children);
	}

	public ASTNode operand() {
		return child(0);
	}

	public Object evaluate(Environment environment) {
		return evaluateSubExpression(environment, 0);
	}

	private Object evaluateSubExpression(Environment environment, int nest) {
		if (hasPostfix(nest)) {
			Object target = evaluateSubExpression(environment, nest + 1);
			return postfix(nest).evaluate(environment, target);
		}
		return operand().evaluate(environment);
	}

	private Postfix postfix(int nest) {
		return (Postfix) child(numChildren() - nest - 1);
	}

	private boolean hasPostfix(int nest) {
		return numChildren() - nest > 1;
	}
}
