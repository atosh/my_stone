package stone.ast;

import stone.env.Environment;
import stone.lexer.Token;

public class StringLiteral extends ASTLeaf {

	public StringLiteral(Token token) {
		super(token);
	}

	public String value() {
		return token().getText();
	}

	@Override
	public Object evaluate(Environment environment) {
		return value();
	}

}
