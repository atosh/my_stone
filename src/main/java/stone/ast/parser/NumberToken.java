package stone.ast.parser;

import stone.Token;
import stone.ast.ASTLeaf;

public class NumberToken extends AbstractToken {

	protected NumberToken(Class<? extends ASTLeaf> type) {
		super(type);
	}

	@Override
	protected boolean test(Token token) {
		return token.isNumber();
	}

}
