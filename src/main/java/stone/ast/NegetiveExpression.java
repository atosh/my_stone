package stone.ast;

import java.util.List;

import stone.env.Environment;
import stone.lexer.StoneException;

public class NegetiveExpression extends ASTList {

	public NegetiveExpression(List<ASTNode> children) {
		super(children);
	}

	public ASTNode operand() {
		return child(0);
	}

	public String toString() {
		return "-" + operand();
	}

	@Override
	public Object evaluate(Environment environment) {
		Object value = operand().evaluate(environment);
		if (value instanceof Integer) {
			return new Integer(-((Integer) value).intValue());
		}
		throw new StoneException("bad type for -", this);
	}

}
