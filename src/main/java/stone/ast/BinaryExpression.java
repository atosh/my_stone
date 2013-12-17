package stone.ast;

import java.util.List;

import stone.env.Environment;
import stone.lexer.StoneException;

public class BinaryExpression extends ASTList {

	public BinaryExpression(List<ASTNode> children) {
		super(children);
	}

	public ASTNode left() {
		return child(0);
	}

	public String operator() {
		return ((ASTLeaf) child(1)).token().getText();
	}

	public ASTNode right() {
		return child(2);
	}

	@Override
	public Object evaluate(Environment environment) {
		String op = operator();
		if ("=".equals(op)) {
			Object right = right().evaluate(environment);
			return computeAssign(environment, right);
		}
		Object left = left().evaluate(environment);
		Object right = right().evaluate(environment);
		return computeOp(left, op, right);
	}

	protected Object computeAssign(Environment environment, Object right) {
		ASTNode leftNode = left();
		if (leftNode instanceof Name) {
			environment.put(((Name) leftNode).name(), right);
			return right;
		}
		throw new StoneException("bad assignment", this);
	}

	protected Object computeOp(Object left, String op, Object right) {
		if (left instanceof Integer && right instanceof Integer) {
			return computeNumber((Integer) left, op, (Integer) right);
		}
		if ("+".equals(op)) {
			return String.valueOf(left) + String.valueOf(right);
		} else if ("==".equals(op)) {
			if (left == null) {
				return right == null;
			}
			return left.equals(right);
		}
		throw new StoneException("bad type", this);
	}

	protected Object computeNumber(Integer left, String op, Integer right) {
		int a = left.intValue();
		int b = right.intValue();
		if ("+".equals(op)) {
			return a + b;
		} else if ("-".equals(op)) {
			return a - b;
		} else if ("*".equals(op)) {
			return a * b;
		} else if ("/".equals(op)) {
			return a / b;
		} else if ("%".equals(op)) {
			return a % b;
		} else if ("==".equals(op)) {
			return a == b;
		} else if (">".equals(op)) {
			return a > b;
		} else if ("<".equals(op)) {
			return a < b;
		}
		throw new StoneException("bad operator", this);
	}

}
