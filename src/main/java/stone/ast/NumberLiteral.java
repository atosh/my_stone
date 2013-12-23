package stone.ast;

import stone.env.IEnv;
import stone.lexer.Token;

public class NumberLiteral extends ASTLeaf {

	public NumberLiteral(Token token) {
		super(token);
	}

	public int value() {
		return token().getNumber();
	}

	@Override
	public Object evaluate(IEnv env) {
		return value();
	}

}
