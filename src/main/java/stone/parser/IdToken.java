package stone.parser;

import java.util.HashSet;

import stone.ast.ASTLeaf;
import stone.lexer.Token;

public class IdToken extends AToken {
	private HashSet<String> _reserved;

	protected IdToken(Class<? extends ASTLeaf> type, HashSet<String> reserved) {
		super(type);
		_reserved = reserved != null ? reserved : new HashSet<String>();
	}

	@Override
	protected boolean test(Token token) {
		return token.isIdentifier() && !_reserved.contains(token.getText());
	}
}
