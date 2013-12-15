package stone.ast.parser;

import stone.Token;
import stone.ast.ASTLeaf;

public class StringToken extends AbstractToken {

	protected StringToken(Class<? extends ASTLeaf> type) {
		super(type);
	}

	@Override
	protected boolean test(Token token) {
		return token.isString();
	}

}
