package stone.parser;

import stone.ast.ASTLeaf;
import stone.lexer.Token;

public class StringToken extends AbstractToken {

	protected StringToken(Class<? extends ASTLeaf> type) {
		super(type);
	}

	@Override
	protected boolean test(Token token) {
		return token.isString();
	}

}
