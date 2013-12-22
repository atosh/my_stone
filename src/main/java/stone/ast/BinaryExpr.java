package stone.ast;

import java.util.List;

import stone.env.Env;
import stone.lexer.StoneException;
import stone.parser.StoneObject;
import stone.parser.StoneObject.AccessException;

public class BinaryExpr extends ASTList {

	public BinaryExpr(List<ASTNode> children) {
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
	public Object evaluate(Env env) {
		String op = operator();
		if ("=".equals(op)) {
			Object right = right().evaluate(env);
			return computeAssign(env, right);
		}
		Object left = left().evaluate(env);
		Object right = right().evaluate(env);
		return computeOp(left, op, right);
	}

	protected Object computeAssign(Env env, Object right) {
		ASTNode leftNode = left();
		if (leftNode instanceof PrimaryExpr) {
			PrimaryExpr expr = (PrimaryExpr) leftNode;
			if (expr.hasPostfix(0) && expr.postfix(0) instanceof Dot) {
				Object object = expr.evaluateSubExpr(env, 1);
				if (object instanceof StoneObject) {
					return setField((StoneObject) object,
							(Dot) expr.postfix(0), right);
				}
			}
		}
		if (leftNode instanceof Name) {
			env.put(((Name) leftNode).name(), right);
			return right;
		}
		throw new StoneException("bad assignment", this);
	}

	protected Object setField(StoneObject object, Dot expr, Object right) {
		String name = expr.name();
		try {
			object.write(name, right);
			return right;
		} catch (AccessException e) {
			throw new StoneException("bad member access " + location() + ": "
					+ name);
		}
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
