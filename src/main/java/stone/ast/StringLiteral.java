package stone.ast;

import stone.env.IEnv;
import stone.lexer.Token;

public class StringLiteral extends ASTLeaf {
	public StringLiteral(Token token) {
		super(token);
	}

	public String value() {
		return token().getText();
	}

	@Override
	public Object evaluate(IEnv env) {
		return value();
	}
}
