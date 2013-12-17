package stone.ast;

import stone.env.Environment;
import stone.lexer.Token;

public class NumberLiteral extends ASTLeaf {

	public NumberLiteral(Token token) {
		super(token);
	}

	public int value() {
		return token().getNumber();
	}

	@Override
	public Object evaluate(Environment environment) {
		return value();
	}

}
