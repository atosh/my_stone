package stone.ast;

import java.util.List;

import stone.env.IEnv;
import stone.lexer.StoneException;

public class NegativeExpr extends ASTList {
	public NegativeExpr(List<ASTNode> children) {
		super(children);
	}

	public ASTNode operand() {
		return child(0);
	}

	public String toString() {
		return "-" + operand();
	}

	@Override
	public Object evaluate(IEnv env) {
		Object value = operand().evaluate(env);
		if (value instanceof Integer) {
			return new Integer(-((Integer) value).intValue());
		}
		throw new StoneException("bad type for -", this);
	}
}
