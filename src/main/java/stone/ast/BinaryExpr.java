package stone.ast;

import java.util.List;

import stone.env.IEnv;
import stone.env.Symbols;
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
	
	public void lookup(Symbols symbols) {
		ASTNode leftNode = left();
		if ("=".equals(operator())) {
			if (leftNode instanceof Name) {
				((Name) leftNode).lookupForAssign(symbols);
				right().lookup(symbols);
				return;
			}
		}
		leftNode.lookup(symbols);
		right().lookup(symbols);
	}

	@Override
	public Object evaluate(IEnv env) {
		String op = operator();
		if ("=".equals(op)) {
			Object right = right().evaluate(env);
			return computeAssign(env, right);
		}
		Object left = left().evaluate(env);
		Object right = right().evaluate(env);
		return computeOp(left, op, right);
	}

	protected Object computeAssign(IEnv env, Object right) {
		ASTNode leftNode = left();
		if (leftNode instanceof Name) {
			((Name) leftNode).evalForAssign(env, right);
			return right;
		}
		if (leftNode instanceof PrimaryExpr) {
			PrimaryExpr expr = (PrimaryExpr) leftNode;
			if (expr.hasPostfix(0) && expr.postfix(0) instanceof ArrayRef) {
				Object object = expr.evaluateSubExpr(env, 1);
				if (object instanceof Object[]) {
					ArrayRef ref = (ArrayRef) expr.postfix(0);
					Object index = ref.index().evaluate(env);
					if (index instanceof Integer) {
						((Object[]) object)[(int) index] = right;
						return right;
					}
				}
				throw new StoneException("bad array access", this);
			}
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
