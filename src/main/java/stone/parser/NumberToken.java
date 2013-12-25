package stone.parser;

import stone.ast.ASTLeaf;
import stone.lexer.Token;

public class NumberToken extends AToken {

	protected NumberToken(Class<? extends ASTLeaf> type) {
		super(type);
	}

	@Override
	protected boolean test(Token token) {
		return token.isNumber();
	}

}
